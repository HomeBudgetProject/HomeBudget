package ua.com.homebudget.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The entity class for the "account_balances" database view.
 *
 * @author Stefansky Alex
 * @author Bondar Dmytro
 */
@Entity
@Table(name="account_balances")
public class AccountBalance implements Serializable {

	private static final long serialVersionUID = -8355490469952552432L;

	@Id
	@SequenceGenerator(name="ACCOUNT_ID_GENERATOR", sequenceName="ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ACCOUNT_ID_GENERATOR")
	@Column(name="account_id")
	private Integer accountId;

    @Column(name="user_id")
    private Integer userId;

    @Column(name="name")
    private String name;

	@Column(name="balance")
	private BigDecimal balance;

    public Integer getAccountId() {
		return this.accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountBalance)) return false;

        AccountBalance that = (AccountBalance) o;

        if (!getAccountId().equals(that.getAccountId())) return false;
        if (getBalance() != null ? !getBalance().equals(that.getBalance()) : that.getBalance() != null) return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        return getUserId().equals(that.getUserId());

    }

    @Override
    public int hashCode() {
        int result = getAccountId().hashCode();
        result = 31 * result + (getBalance() != null ? getBalance().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + getUserId().hashCode();
        return result;
    }
	
}