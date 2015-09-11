package ua.com.homebudget.service;

import ua.com.homebudget.dto.ExpenseCategoryRequest;
import ua.com.homebudget.model.ExpenseCategory;

import java.util.List;

/**
 * @author Bondar Dmytro
 */

public interface ExpenseCategoryService {

    public List<ExpenseCategory> getCategories();

    public ExpenseCategory getCategory(Integer id);

    public void add(ExpenseCategoryRequest request);

    public void delete (Integer id);

    public void changeName(Integer id, String newName);

    public void changeParent(Integer id, Integer newParent);

    public List<ExpenseCategory> getUsersCategory(Integer id);

    public List<ExpenseCategory> getChildesCategory(Integer id);

}
