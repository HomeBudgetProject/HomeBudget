package ua.com.homebudget.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 * The entity class for the "incomes" database table.
 *
 * @author Stefansky Alex
 * @author Bondar Dmytro
 */
@Entity
@Table(name = "incomes")
public class Income implements Serializable {

    private static final long serialVersionUID = -4442634724510563026L;

    @Id
    @SequenceGenerator(name = "INCOME_ID_GENERATOR", sequenceName = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INCOME_ID_GENERATOR")
    @Column(name = "income_id")
    private Integer incomeId;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private IncomeCategory incomeCategory;

    @NotNull
    @Column(name = "datetime")
    private Timestamp datetime;

    @NotEmpty
    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private BigDecimal value;

    @ManyToMany
    @JoinTable(
            name = "tags_in_incomes",
            joinColumns = {@JoinColumn(name = "income_id", referencedColumnName = "income_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id", referencedColumnName = "tag_id")})
    private List<IncomeTag> incomes;

    public Integer getIncomeId() {
        return this.incomeId;
    }

    public void setIncomeId(Integer incomeId) {
        this.incomeId = incomeId;
    }

    public Account getAccount() {
        return this.account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public IncomeCategory getIncomeCategory() {
        return this.incomeCategory;
    }

    public void setIncomeCategory(IncomeCategory incomeCategory) {
        this.incomeCategory = incomeCategory;
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

    public List<IncomeTag> getIncomes() {
        return incomes;
    }

    public void setIncomes(List<IncomeTag> incomes) {
        this.incomes = incomes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Income)) return false;

        Income income = (Income) o;

        if (!getIncomeId().equals(income.getIncomeId())) return false;
        if (!getDatetime().equals(income.getDatetime())) return false;
        if (!getName().equals(income.getName())) return false;
        if (getValue() != null ? !getValue().equals(income.getValue()) : income.getValue() != null) return false;
        if (!getAccount().equals(income.getAccount())) return false;
        if (!getIncomeCategory().equals(income.getIncomeCategory())) return false;
        return !(getIncomes() != null ? !getIncomes().equals(income.getIncomes()) : income.getIncomes() != null);

    }

    @Override
    public int hashCode() {
        int result = getIncomeId().hashCode();
        result = 31 * result + getDatetime().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + (getValue() != null ? getValue().hashCode() : 0);
        result = 31 * result + getAccount().hashCode();
        result = 31 * result + getIncomeCategory().hashCode();
        result = 31 * result + (getIncomes() != null ? getIncomes().hashCode() : 0);
        return result;
    }
}