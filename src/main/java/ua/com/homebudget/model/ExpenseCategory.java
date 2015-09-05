package ua.com.homebudget.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


/**
 * The entity class for the "expense_categories" database table.
 *
 * @author Stefansky Alex
 * @author Bondar Dmytro
 */
@Data
@Entity
@Table(name = "expense_categories")
@EqualsAndHashCode(exclude = {"children", "expenses"})
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

    @JsonIgnore
    @OneToMany(mappedBy = "parentId")
    private Set<ExpenseCategory> children;

    @JsonIgnore
    @OneToMany(mappedBy = "expenseCategory")
    private Set<Expense> expenses;

}