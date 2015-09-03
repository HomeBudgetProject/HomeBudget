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

    @OneToMany(mappedBy = "parentId")
    private List<ExpenseCategory> children;

    @OneToMany(mappedBy = "expenseCategory")
    private List<Expense> expenses;

}