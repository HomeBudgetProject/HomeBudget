package ua.com.homebudget.dto;

import lombok.Data;

/**
 * @author Bondar Dmytro.
 */
@Data
public class ExpenseCategoryRequest {

    private Integer parentId;

    private Integer user;

    private String name;
}
