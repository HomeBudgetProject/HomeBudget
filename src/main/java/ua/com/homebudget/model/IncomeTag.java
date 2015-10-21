package ua.com.homebudget.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


/**
 * The entity class for the "income_tags" database table.
 *
 * @author Stefansky Alex
 * @author Bondar Dmytro
 */
@Data
@Entity
@Table(name = "income_tags")
@EqualsAndHashCode(exclude = {"incomes"})
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

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tags_in_incomes",
            joinColumns = {@JoinColumn(name = "tag_id", referencedColumnName = "tag_id")},
            inverseJoinColumns = {@JoinColumn(name = "income_id", referencedColumnName = "income_id")})
    private Set<Income> incomes;

}
