package ua.com.homebudget.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * The entity class for the "expense_tags" database table.
 *
 * @author Stefansky Alex
 * @author Bondar Dmytro
 */
@Entity
@Table(name = "expense_tags")
public class ExpenseTag implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "TAG_ID_GENERATOR", sequenceName = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TAG_ID_GENERATOR")
    @Column(name = "tag_id")
    private Integer tagId;

    @NotEmpty
    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "tags_in_expenses",
            joinColumns = {@JoinColumn(name = "tag_id", referencedColumnName = "tag_id")},
            inverseJoinColumns = {@JoinColumn(name = "expense_id", referencedColumnName = "expense_id")})
    private List<Expense> expenses;

    public Integer getTagId() {
        return this.tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((tagId == null) ? 0 : tagId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ExpenseTag other = (ExpenseTag) obj;
        if (tagId == null) {
            if (other.tagId != null) {
                return false;
            }
        } else if (!tagId.equals(other.tagId)) {
            return false;
        }
        return true;
    }

}