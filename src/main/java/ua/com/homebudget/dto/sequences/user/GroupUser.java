package ua.com.homebudget.dto.sequences.user;

import javax.validation.GroupSequence;

/**
 * @author Bondar Dmytro.
 */
@GroupSequence({GroupUser.EmailStepOne.class, GroupUser.EmailStepTwo.class, GroupUser.PasswordStepOne.class})
public interface GroupUser {

    interface EmailStepOne {
    }

    interface EmailStepTwo {
    }

    interface PasswordStepOne {
    }

}
