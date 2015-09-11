package ua.com.homebudget.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.homebudget.model.ExpenseCategory;

import java.util.List;

/**
 * Repository for expense category
 *
 * @author Bondar Dmytro.
 */
public interface ExpenseCategoryRepository extends JpaRepository<ExpenseCategory, Integer> {

    List<ExpenseCategory> findByParentId_CategoryId(int id);

    List<ExpenseCategory> findByUser_UserId(int id);
}
