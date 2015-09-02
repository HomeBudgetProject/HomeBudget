package ua.com.homebudget.model;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 * The entity class for the "expenses" database table.
 *
 * @author Stefansky Alex
 * @author Bondar Dmytro
 */
@Entity
@Table(name="expenses")
public class Expense implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EXPENSE_ID_GENERATOR", sequenceName="ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EXPENSE_ID_GENERATOR")
	@Column(name="expense_id")
	private Integer expenseId;

    @ManyToOne
    @JoinColumn(name="account_Id")
    private Account account;

    @ManyToOne
    @JoinColumn(name="category_id")
    private ExpenseCategory expenseCategory;

	@NotNull
	@Column(name = "datetime")
	private Timestamp datetime;

	@NotEmpty
	@Column(name = "name")
	private String name;

	@NotNull
	@Column(name = "value")
	private BigDecimal value;

    @ManyToMany
    @JoinTable(
            name="tags_in_incomes",
            joinColumns={@JoinColumn(name="expense_id", referencedColumnName="expense_id")},
            inverseJoinColumns={@JoinColumn(name="tag_id", referencedColumnName="tag_id")})
	private List<ExpenseTag> expenseTags;

	public Integer getExpenseId() {
		return this.expenseId;
	}

	public void setExpenseId(Integer expenseId) {
		this.expenseId = expenseId;
	}

    public Account getAccount() {
        return this.account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public ExpenseCategory getExpenseCategory() {
        return this.expenseCategory;
    }

    public void setExpenseCategory(ExpenseCategory expenseCategory) {
        this.expenseCategory = expenseCategory;
    }

	public Timestamp getDatetime() {
		return this.datetime;
	}

	public void setDatetime(Timestamp datetime) {
		this.datetime = datetime;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getValue() {
		return this.value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public List<ExpenseTag> getExpenseTags() {
		return this.expenseTags;
	}

	public void setExpenseTags(List<ExpenseTag> expenseTags) {
		this.expenseTags = expenseTags;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Expense)) return false;

		Expense expense = (Expense) o;

		if (!getExpenseId().equals(expense.getExpenseId())) return false;
		if (!getDatetime().equals(expense.getDatetime())) return false;
		if (getName() != null ? !getName().equals(expense.getName()) : expense.getName() != null) return false;
		if (getValue() != null ? !getValue().equals(expense.getValue()) : expense.getValue() != null) return false;
		if (!getAccount().equals(expense.getAccount())) return false;
		return getExpenseCategory().equals(expense.getExpenseCategory());

	}

	@Override
	public int hashCode() {
		int result = getExpenseId().hashCode();
		result = 31 * result + getDatetime().hashCode();
		result = 31 * result + (getName() != null ? getName().hashCode() : 0);
		result = 31 * result + (getValue() != null ? getValue().hashCode() : 0);
		result = 31 * result + getAccount().hashCode();
		result = 31 * result + getExpenseCategory().hashCode();
		return result;
	}

}