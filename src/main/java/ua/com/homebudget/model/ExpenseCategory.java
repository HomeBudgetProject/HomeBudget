package ua.com.homebudget.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * The entity class for the "expense_categories" database table.
 *
 * @author Stefansky Alex
 * @author Bondar Dmytro
 */
@Entity
@Table(name = "expense_categories")
public class ExpenseCategory implements Serializable {

    private static final long serialVersionUID = 1471057802834876618L;

    @Id
    @SequenceGenerator(name = "CATEGORY_ID_GENERATOR", sequenceName = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORY_ID_GENERATOR")
    @Column(name = "category_id")
    private Integer categoryId;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private ExpenseCategory parentId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "expense_category")
    private List<ExpenseCategory> children;

    @OneToMany(mappedBy = "expense_category")
    private List<Expense> expenses;

    public Integer getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public ExpenseCategory getParentId() {
        return parentId;
    }

    public void setParentId(ExpenseCategory parentId) {
        this.parentId = parentId;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ExpenseCategory> getChildren() {
        return children;
    }

    public void setChildren(List<ExpenseCategory> children) {
        this.children = children;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExpenseCategory)) return false;

        ExpenseCategory that = (ExpenseCategory) o;

        if (!getCategoryId().equals(that.getCategoryId())) return false;
        if (!getName().equals(that.getName())) return false;
        if (!getUser().equals(that.getUser())) return false;
        return !(getParentId() != null ? !getParentId().equals(that.getParentId()) : that.getParentId() != null);

    }

    @Override
    public int hashCode() {
        int result = getCategoryId().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getUser().hashCode();
        result = 31 * result + (getParentId() != null ? getParentId().hashCode() : 0);
        return result;
    }
}