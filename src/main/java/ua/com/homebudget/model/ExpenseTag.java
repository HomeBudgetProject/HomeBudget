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

}