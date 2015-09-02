package ua.com.homebudget.model;


import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;


/**
 * The entity class for the "income_tags" database table.
 *
 * @author Stefansky Alex
 * @author Bondar Dmytro
 */
@Entity
@Table(name="income_tags")
public class IncomeTag implements Serializable {

    private static final long serialVersionUID = -6249092761106522119L;

    @Id
    @SequenceGenerator(name="TAG_ID_GENERATOR", sequenceName="ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TAG_ID_GENERATOR")
    @Column(name="tag_id")
    private Integer tagId;

    @NotEmpty
    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(
            name="tags_in_incomes",
            joinColumns={@JoinColumn(name="tag_id", referencedColumnName="tag_id")},
            inverseJoinColumns={@JoinColumn(name="income_id", referencedColumnName="income_id")})
    private List<Income> incomes;

    public Integer getTagId() {
        return this.tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Income> getIncomes() {
        return incomes;
    }

    public void setIncomes(List<Income> incomes) {
        this.incomes = incomes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IncomeTag)) return false;

        IncomeTag incomeTag = (IncomeTag) o;

        if (!getTagId().equals(incomeTag.getTagId())) return false;
        return getName().equals(incomeTag.getName());

    }

    @Override
    public int hashCode() {
        int result = getTagId().hashCode();
        result = 31 * result + getName().hashCode();
        return result;
    }
}
