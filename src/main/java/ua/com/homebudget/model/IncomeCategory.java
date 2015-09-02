package ua.com.homebudget.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * The entity class for the "income_categories" database table.
 *
 * @author Stefansky Alex
 * @author Bondar Dmytro
 */
@Entity
@Table(name = "income_categories")
public class IncomeCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "CATEGORY_ID_GENERATOR", sequenceName = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORY_ID_GENERATOR")
    @Column(name = "category_id")
    private Integer categoryId;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private IncomeCategory parentId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotEmpty
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "parentId")
    private List<IncomeCategory> children;

    @OneToMany(mappedBy = "incomeCategory")
    private List<Income> incomes;

    public Integer getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public IncomeCategory getParentId() {
        return parentId;
    }

    public void setParentId(IncomeCategory parentId) {
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

    public List<IncomeCategory> getChildren() {
        return children;
    }

    public void setChildren(List<IncomeCategory> children) {
        this.children = children;
    }

    public List<Income> getIncomes() {
        return incomes;
    }

    public void setIncomes(List<Income> incomes) {
        this.incomes = incomes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IncomeCategory)) return false;

        IncomeCategory that = (IncomeCategory) o;

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