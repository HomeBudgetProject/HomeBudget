package ua.com.homebudget.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.com.homebudget.dto.ExpenseCategoryRequest;
import ua.com.homebudget.dto.IncomeCategoryRequest;
import ua.com.homebudget.model.ExpenseCategory;
import ua.com.homebudget.model.IncomeCategory;
import ua.com.homebudget.service.ExpenseCategoryService;
import ua.com.homebudget.service.IncomeCategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    IncomeCategoryService incomeCategoryService;

    @Autowired
    ExpenseCategoryService expenseCategoryService;

    @RequestMapping(value = "/income/add", method = RequestMethod.POST)
    public void registerIncoem(@RequestBody IncomeCategoryRequest request) {
        incomeCategoryService.add(request);
    }

    @RequestMapping(value = "/income/{id}", method = RequestMethod.DELETE)
    public void deleteIncome(@PathVariable("id") Integer id) {
        incomeCategoryService.delete(id);
    }

    @RequestMapping(value = "/income/id={id}&newName={newName}", method = RequestMethod.PUT)
    public void changeIncomeName(@PathVariable("id") Integer id, @PathVariable("newName") String newName) {
        incomeCategoryService.changeName(id, newName);
    }

    @RequestMapping(value = "/income/id={id}&newParent={newParent}", method = RequestMethod.PUT)
    public void changeIncomeParent(@PathVariable("id") Integer id, @PathVariable("newParent") Integer newParent) {
        incomeCategoryService.changeParent(id, newParent);
    }

    @RequestMapping("/income/")
    public List<IncomeCategory> getIncomeCategories() {
        return incomeCategoryService.getCategories();
    }

    @RequestMapping("/income/{id}")
    public IncomeCategory getIncomeCategory(@PathVariable Integer id) {
        return incomeCategoryService.getCategory(id);
    }

    @RequestMapping("/income/user/{id}")
    public List<IncomeCategory> getUserIncomeCategory(@PathVariable Integer id) {
        return incomeCategoryService.getUsersCategory(id);
    }

    @RequestMapping("/income/parent/{id}")
    public List<IncomeCategory> getChildesIncomeCategory(@PathVariable Integer id) {
        return incomeCategoryService.getChildesCategory(id);
    }

    @RequestMapping(value = "/expense/add", method = RequestMethod.POST)
    public void registerExpense(@RequestBody ExpenseCategoryRequest request) {
        expenseCategoryService.add(request);
    }

    @RequestMapping(value = "/expense/{id}", method = RequestMethod.DELETE)
    public void deleteExpense(@PathVariable("id") Integer id) {
        expenseCategoryService.delete(id);
    }

    @RequestMapping(value = "/expense/id={id}&newName={newName}", method = RequestMethod.PUT)
    public void changeExpenseName(@PathVariable("id") Integer id, @PathVariable("newName") String newName) {
        expenseCategoryService.changeName(id, newName);
    }

    @RequestMapping(value = "/expense/id={id}&newParent={newParent}", method = RequestMethod.PUT)
    public void changeExpenseParent(@PathVariable("id") Integer id, @PathVariable("newParent") Integer newParent) {
        expenseCategoryService.changeParent(id, newParent);
    }

    @RequestMapping("/expense/")
    public List<ExpenseCategory> getExpenseCategories() {
        return expenseCategoryService.getCategories();
    }

    @RequestMapping("/expense/{id}")
    public ExpenseCategory getExpenseCategory(@PathVariable Integer id) {
        return expenseCategoryService.getCategory(id);
    }

    @RequestMapping("/expense/user/{id}")
    public List<ExpenseCategory> getUserExpenseCategory(@PathVariable Integer id) {
        return expenseCategoryService.getUsersCategory(id);
    }

    @RequestMapping("/expense/parent/{id}")
    public List<ExpenseCategory> getChildesExpenseCategory(@PathVariable Integer id) {
        return expenseCategoryService.getChildesCategory(id);
    }

}
