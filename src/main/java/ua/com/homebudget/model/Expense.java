package ua.com.homebudget.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;


/**
 * The entity class for the "expenses" database table.
 *
 * @author Stefansky Alex
 * @author Bondar Dmytro
 */
@Data
@Entity
@Table(name = "expenses")
@EqualsAndHashCode(exclude = {"expenseTags"})
public class Expense implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "EXPENSE_ID_GENERATOR", sequenceName = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXPENSE_ID_GENERATOR")
    @Column(name = "expense_id")
    private Integer expenseId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_Id")
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
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

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tags_in_incomes",
            joinColumns = {@JoinColumn(name = "expense_id", referencedColumnName = "expense_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id", referencedColumnName = "tag_id")})
    private Set<ExpenseTag> expenseTags;

}