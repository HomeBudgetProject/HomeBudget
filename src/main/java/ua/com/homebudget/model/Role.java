package ua.com.homebudget.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * The entity class for the "roles" database table.
 *
 * @author Stefansky Alex
 * @author Bondar Dmytro
 */
@Entity
@Table(name = "roles")
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "ROLE_ID_GENERATOR", sequenceName = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROLE_ID_GENERATOR")
    @Column(name = "role_id")
    private Integer roleId;

    @NotEmpty
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "userRole")
    private List<User> users;

    public Integer getRoleId() {
        return this.roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;

        Role role = (Role) o;

        if (!getRoleId().equals(role.getRoleId())) return false;
        return getName().equals(role.getName());

    }

    @Override
    public int hashCode() {
        int result = getRoleId().hashCode();
        result = 31 * result + getName().hashCode();
        return result;
    }
}