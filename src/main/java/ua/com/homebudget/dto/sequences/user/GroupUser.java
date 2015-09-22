package ua.com.homebudget.dto.sequences.user;

import javax.validation.GroupSequence;

/**
 * @author Bondar Dmytro.
 */
@GroupSequence({EmailStep1.class, EmailStep2.class, PasswordStep1.class})
public interface GroupUser {
}
