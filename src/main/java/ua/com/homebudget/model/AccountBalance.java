package ua.com.homebudget.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;


/**
 * The entity class for the "account_balances" database view.
 *
 * @author Stefansky Alex
 * @author Bondar Dmytro
 */
@Entity
@Table(name = "account_balances")
public class AccountBalance implements Serializable {

    private static final long serialVersionUID = -8355490469952552432L;

    @Id
    @SequenceGenerator(name = "ACCOUNT_ID_GENERATOR", sequenceName = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCOUNT_ID_GENERATOR")
    @Column(name = "account_id")
    private Integer accountId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "name")
    private String name;

    @Column(name = "balance")
    private BigDecimal balance;

}