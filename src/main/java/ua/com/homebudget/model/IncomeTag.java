package ua.com.homebudget.model;


import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * The entity class for the "income_tags" database table.
 *
 * @author Stefansky Alex
 * @author Bondar Dmytro
 */
@Entity
@Table(name = "income_tags")
public class IncomeTag implements Serializable {

    private static final long serialVersionUID = -6249092761106522119L;

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
            name = "tags_in_incomes",
            joinColumns = {@JoinColumn(name = "tag_id", referencedColumnName = "tag_id")},
            inverseJoinColumns = {@JoinColumn(name = "income_id", referencedColumnName = "income_id")})
    private List<Income> incomes;

}
