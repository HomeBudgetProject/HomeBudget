package ua.com.homebudget.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class IncomeCategoryServiceException extends RuntimeException {
    public IncomeCategoryServiceException() {
        super();
    }

    public IncomeCategoryServiceException(String message) {
        super(message);
    }

    public IncomeCategoryServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncomeCategoryServiceException(Throwable cause) {
        super(cause);
    }

    protected IncomeCategoryServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
