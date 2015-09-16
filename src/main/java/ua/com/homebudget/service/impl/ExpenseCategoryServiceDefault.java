package ua.com.homebudget.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.homebudget.dto.ExpenseCategoryRequest;
import ua.com.homebudget.exception.ExpenseCategoryServiceException;
import ua.com.homebudget.model.ExpenseCategory;
import ua.com.homebudget.repository.ExpenseCategoryRepository;
import ua.com.homebudget.repository.UserRepository;
import ua.com.homebudget.service.ExpenseCategoryService;

import java.util.List;

/**
 * @author Bondar Dmytro.
 */
@Component
public class ExpenseCategoryServiceDefault implements ExpenseCategoryService {

    @Autowired
    ExpenseCategoryRepository categoryRepository;

    @Autowired
    UserRepository userRepository;

    public List<ExpenseCategory> getCategories() {
        return categoryRepository.findAll();
    }

    public ExpenseCategory getCategory(Integer id) {
        return categoryRepository.findOne(id);
    }

    public void add(ExpenseCategoryRequest request) {
        ExpenseCategory category = new ExpenseCategory();
        category.setUser(userRepository.findOne(request.getUser()));
        if (request.getParentId() != null) {
            ExpenseCategory parent = categoryRepository.findOne(request.getParentId());
            if (parent.getParentId() != null)
                throw new ExpenseCategoryServiceException("Can't add a subcategory for subcategory");
            category.setParentId(parent);
        } else category.setParentId(null);
        category.setName(request.getName());
        categoryRepository.save(category);
    }

    public void delete(Integer id) {
        if (categoryRepository.findOne(id) == null)
            throw new ExpenseCategoryServiceException("There is no such category");
        categoryRepository.delete(id);
    }

    public void changeName(Integer id, String newName) {
        if (categoryRepository.findOne(id) == null)
            throw new ExpenseCategoryServiceException("There is no such category");
        ExpenseCategory category = categoryRepository.findOne(id);
        category.setName(newName);
    }

    public void changeParent(Integer id, Integer newParent) {
        if (categoryRepository.findOne(id) == null)
            throw new ExpenseCategoryServiceException("There is no such category");
        ExpenseCategory category = categoryRepository.findOne(id);
        ExpenseCategory parent = null;
        if (newParent != null) {
            parent = categoryRepository.findOne(newParent);
            if (parent == null)
                throw new ExpenseCategoryServiceException("There is no such parent category");
        }
        category.setParentId(parent);
    }

    public List<ExpenseCategory> getUsersCategory(Integer id) {
        return categoryRepository.findByUserUserId(id);
    }

    public List<ExpenseCategory> getChildesCategory(Integer id) {
        return categoryRepository.findByParentIdCategoryId(id);
    }
}
