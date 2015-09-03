package ua.com.homebudget.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * The entity class for the "income_categories" database table.
 *
 * @author Stefansky Alex
 * @author Bondar Dmytro
 */
@Data
@Entity
@Table(name = "income_categories")
public class IncomeCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "CATEGORY_ID_GENERATOR", sequenceName = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORY_ID_GENERATOR")
    @Column(name = "category_id")
    private Integer categoryId;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private IncomeCategory parentId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotEmpty
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "parentId")
    private Set<IncomeCategory> children;

    @OneToMany(mappedBy = "incomeCategory")
    private Set<Income> incomes;

}