package ua.com.homebudget.repository;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import ua.com.homebudget.dto.ChangePasswordRequestToken;
import ua.com.homebudget.exception.UserServiceException;

@Component
@Scope(value = "singleton")
@Slf4j
public class ChangePasswordRequestRepository {

    public static final Integer TOKEN_LIFE_TIME_IN_HOURS = 5;

    private ConcurrentHashMap<String, ChangePasswordRequestToken> container = new ConcurrentHashMap<>();

    public String issueToken(String email) {
        ChangePasswordRequestToken token = new ChangePasswordRequestToken();

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.HOUR_OF_DAY, TOKEN_LIFE_TIME_IN_HOURS);
        Date expirationTime = cal.getTime();

        token.setExpirationTime(expirationTime);
        token.setEmail(email);

        String digest = getDigest(token);
        container.put(digest, token);

        return digest;
    }

    /**
     * Checks if specified token registered within repository and not expired.
     * Purges the token from the repository if it is expired. Returns email
     * associated with the token if the token is valid and throws exception
     * otherwise
     * 
     * @param digest
     *            a hash corresponding to a registered token
     * @return email associated with the token if the token is valid, otherwise,
     *         throws exception
     */
    public String checkTokenValidityAndGetEmail(String digest) {
        log.debug("Validating a token for the digest {}...", digest);
        String result = null;

        if (digest != null && container.containsKey(digest)) {
            ChangePasswordRequestToken token = container.get(digest);
            if (new Date().getTime() > token.getExpirationTime().getTime()) {
                log.debug("Token for user {} has expired", token.getEmail());
                purgeToken(digest);
                throw new UserServiceException("Password recovery token has expired");
            } else {
                log.debug("Found valid token for user {}", token.getEmail());
                result = token.getEmail();
            }
        } else {
            log.debug("Token {} not found", digest);
            throw new UserServiceException("Password recovery token has expired");
        }

        return result;
    }

    /**
     * Registers the token and generates string hash for specified token
     * 
     * @param token
     *            change password request token
     * @return hash for specified token
     */
    private String getDigest(ChangePasswordRequestToken token) {
        log.debug("Generating digest for {}...", token);
        String messageToSign = token.getEmail() + token.getExpirationTime().getTime() + UUID.randomUUID().toString();
        String digest = "";
        try {
            MessageDigest m = MessageDigest.getInstance("SHA-256");
            m.update(messageToSign.getBytes(), 0, messageToSign.length());
            digest = URLEncoder.encode(new String(Base64.encode(m.digest())), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("Failed to encode digest: ", e);
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            log.error("Failed to generate digest: ", e);
            throw new RuntimeException(e);
        }

        log.debug("Generated digest for token {} is {}", token, digest);
        return digest;
    }

    public void purgeToken(String digest) {
        if (digest != null && container.containsKey(digest)) {
            container.remove(digest);
        }
    }

}