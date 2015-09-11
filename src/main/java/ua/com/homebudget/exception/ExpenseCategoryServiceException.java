package ua.com.homebudget.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ExpenseCategoryServiceException extends RuntimeException {
    public ExpenseCategoryServiceException() {
        super();
    }

    public ExpenseCategoryServiceException(String message) {
        super(message);
    }

    public ExpenseCategoryServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExpenseCategoryServiceException(Throwable cause) {
        super(cause);
    }

    protected ExpenseCategoryServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
