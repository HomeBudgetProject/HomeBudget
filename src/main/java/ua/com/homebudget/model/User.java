package ua.com.homebudget.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


/**
 * The entity class for the "users" database table.
 *
 * @author Stefansky Alex
 * @author Bondar Dmytro
 */
@Entity
@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = -8038065705883560945L;

    @Id
    @SequenceGenerator(name = "USER_ID_GENERATOR", sequenceName = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_ID_GENERATOR")
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "name")
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    @NotEmpty
    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "user_role")
    private Role userRole;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Account> accounts;

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getUserRole() {
        return this.userRole;
    }

    public void setUserRole(Role userRole) {
        this.userRole = userRole;
    }

    public Set<Account> getAccounts() {
        return this.accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (!getUserId().equals(user.getUserId())) return false;
        if (!getEmail().equals(user.getEmail())) return false;
        if (getName() != null ? !getName().equals(user.getName()) : user.getName() != null) return false;
        if (!getPassword().equals(user.getPassword())) return false;
        if (!getUserRole().equals(user.getUserRole())) return false;
        return !(getAccounts() != null ? !getAccounts().equals(user.getAccounts()) : user.getAccounts() != null);

    }

    @Override
    public int hashCode() {
        int result = getUserId().hashCode();
        result = 31 * result + getEmail().hashCode();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + getPassword().hashCode();
        result = 31 * result + getUserRole().hashCode();
        result = 31 * result + (getAccounts() != null ? getAccounts().hashCode() : 0);
        return result;
    }
}