package ua.com.homebudget.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The entity class for the "accounts" database table.
 *
 * @author Stefansky Alex
 * @author Bondar Dmytro
 */
@Entity
@Table(name="accounts")
public class Account implements Serializable {
	private static final long serialVersionUID = 3206585920990206281L;

	@Id
	@SequenceGenerator(name="ACCOUNT_ID_GENERATOR", sequenceName="ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ACCOUNT_ID_GENERATOR")
	@Column(name="account_id")
	private Integer accountId;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(name="name")
	private String name;

	@OneToMany(mappedBy="account", fetch=FetchType.EAGER)
	private List<Expense> expenses;

	@OneToMany(mappedBy="account", fetch=FetchType.EAGER)
	private List<Income> incomes;

	public Integer getAccountId() {
		return this.accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
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

	public List<Expense> getExpenses() {
		return this.expenses;
	}

	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
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
        if (!(o instanceof Account)) return false;

        Account account = (Account) o;

        if (!getAccountId().equals(account.getAccountId())) return false;
        if (getName() != null ? !getName().equals(account.getName()) : account.getName() != null) return false;
        if (!getUser().equals(account.getUser())) return false;
        if (getExpenses() != null ? !getExpenses().equals(account.getExpenses()) : account.getExpenses() != null)
            return false;
        return !(getIncomes() != null ? !getIncomes().equals(account.getIncomes()) : account.getIncomes() != null);

    }

    @Override
    public int hashCode() {
        int result = getAccountId().hashCode();
        result = 31 * result + getUser().hashCode();
        return result;
    }
}