package ua.com.homebudget.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.homebudget.model.IncomeCategory;

import java.util.List;

/**
 * Repository for income category
 *
 * @author Bondar Dmytro.
 */

public interface IncomeCategoryRepository extends JpaRepository<IncomeCategory, Integer> {

    List<IncomeCategory> findByParentIdCategoryId(int id);

    List<IncomeCategory> findByUserUserId(int id);
}
