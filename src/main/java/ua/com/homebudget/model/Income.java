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

}