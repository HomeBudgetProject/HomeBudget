package ua.com.homebudget.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.homebudget.model.IncomeCategory;

import java.util.List;

/**
 * Repository for income category
 *
 * @author Bondar Dmytro.
 */

public interface IncomeCategoryRepository extends JpaRepository<IncomeCategory, Integer> {

    List<IncomeCategory> findByParentId_CategoryId(int id);

    List<IncomeCategory> findByUser_UserId(int id);
}
