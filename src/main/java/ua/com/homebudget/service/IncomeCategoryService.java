package ua.com.homebudget.service;

import ua.com.homebudget.dto.IncomeCategoryRequest;
import ua.com.homebudget.model.IncomeCategory;

import java.util.List;

/**
 * @author Bondar Dmytro
 */

public interface IncomeCategoryService {

    List<IncomeCategory> getCategories();

    IncomeCategory getCategory(Integer id);

    void add(IncomeCategoryRequest request);

    void delete (Integer id);

    void changeName(Integer id, String newName);

    void changeParent(Integer id, Integer newParent);

    List<IncomeCategory> getUsersCategory(Integer id);

    List<IncomeCategory> getChildesCategory(Integer id);

}
