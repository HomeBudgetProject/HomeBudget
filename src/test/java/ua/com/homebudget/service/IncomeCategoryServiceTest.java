package ua.com.homebudget.service;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.homebudget.DblIntegrationTest;
import ua.com.homebudget.dto.IncomeCategoryRequest;
import ua.com.homebudget.exception.IncomeCategoryServiceException;
import ua.com.homebudget.model.IncomeCategory;
import ua.com.homebudget.repository.IncomeCategoryRepository;

import javax.transaction.Transactional;

@Transactional
@DatabaseSetup("incomeCategory.xml")
public class IncomeCategoryServiceTest extends DblIntegrationTest {

    @Autowired
    IncomeCategoryService service;
    @Autowired
    IncomeCategoryRepository repository;

    @Test
    public void testAddCategory() throws Exception {
        IncomeCategoryRequest request = new IncomeCategoryRequest();
        request.setName("new category");
        request.setParentId(null);
        request.setUser(1);
        service.add(request);
        Assert.assertEquals(4, repository.findAll().size());
    }

    @Test(expected = IncomeCategoryServiceException.class)
    public void testAddSubcategoryForSubcategory() throws Exception {
        IncomeCategoryRequest request = new IncomeCategoryRequest();
        request.setName("new subcategory");
        request.setParentId(2);
        request.setUser(1);
        service.add(request);
    }

    @Test
    public void testDeleteCategory() throws Exception {
        service.delete(2);
        Assert.assertEquals(2, repository.findAll().size());
    }

    @Test(expected = IncomeCategoryServiceException.class)
    public void testDeleteUnexistingCategory() throws Exception {
        service.delete(52);
    }

    @Test
    public void testChangeNameCategory() throws Exception {
        service.changeName(1, "new name");
        Assert.assertEquals("new name", repository.findOne(1).getName());
    }

    @Test(expected = IncomeCategoryServiceException.class)
    public void testChangeNameUnexistingCategory() throws Exception {
        service.changeName(152, "new name");
    }

    @Test
    public void testChangeParentCategories() throws Exception {
        service.changeParent(1, 3);
        IncomeCategory category = repository.findOne(1);
        int parent = category.getParentId().getCategoryId();
        Assert.assertEquals(3, parent);
    }

    @Test
    public void testChangeParentToNull() throws Exception {
        service.changeParent(1, null);
        Assert.assertEquals(null, repository.findOne(1).getParentId());
    }

    @Test(expected = IncomeCategoryServiceException.class)
    public void testChangeParentUnexistingCategory() throws Exception {
        service.changeParent(15, 52);
    }

    @Test(expected = IncomeCategoryServiceException.class)
    public void testChangeParentUnexistingParent() throws Exception {
        service.changeParent(1, 52);
    }

    @Test()
    public void testGetUserCategories() throws Exception {
        Assert.assertEquals(repository.findByUser_UserId(1), service.getUsersCategory(1));
    }

    @Test()
    public void testGetChildesCategories() throws Exception {
        Assert.assertEquals(repository.findByParentId_CategoryId(1), service.getChildesCategory(1));
    }

    @Test()
    public void testGetAllCategories() throws Exception {
        Assert.assertEquals(repository.findAll(), service.getCategories());
    }

    @Test()
    public void testGetCategory() throws Exception {
        Assert.assertEquals(repository.findOne(1), service.getCategory(1));
    }


}