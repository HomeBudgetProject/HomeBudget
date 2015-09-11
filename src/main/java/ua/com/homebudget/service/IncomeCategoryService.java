package ua.com.homebudget.service;

import ua.com.homebudget.dto.IncomeCategoryRequest;
import ua.com.homebudget.dto.UserRequest;
import ua.com.homebudget.model.IncomeCategory;

import java.util.List;

/**
 * @author Bondar Dmytro
 */

public interface IncomeCategoryService {

    public List<IncomeCategory> getCategories();

    public IncomeCategory getCategory(Integer id);

    public void add(IncomeCategoryRequest request);

    public void delete (Integer id);

    public void changeName(Integer id, String newName);

    public void changeParent(Integer id, Integer newParent);

    public List<IncomeCategory> getUsersCategory(Integer id);

    public List<IncomeCategory> getChildesCategory(Integer id);



}
