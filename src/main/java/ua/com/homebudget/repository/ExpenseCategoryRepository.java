package ua.com.homebudget.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.homebudget.model.ExpenseCategory;

import java.util.List;

/**
 * Repository for expense category
 *
 * @author Bondar Dmytro.
 */
public interface ExpenseCategoryRepository extends JpaRepository<ExpenseCategory, Integer> {

    List<ExpenseCategory> findByParentIdCategoryId(int id);

    List<ExpenseCategory> findByUserUserId(int id);
}
