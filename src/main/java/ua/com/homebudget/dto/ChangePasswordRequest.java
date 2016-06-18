package ua.com.homebudget.dto;

import lombok.Data;

/**
 * Created by kart on 15.11.2015.
 */
@Data
public class ChangePasswordRequest {
    private String tokenHash;
    private String newPassword;
}
