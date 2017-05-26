package no.acntech.rules.application.rules;

import no.acntech.rules.application.rules.basis.Gender;
import no.acntech.rules.application.rules.basis.Person;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Rule(name = "IsStrongGenderRule", description = "Checks if the person is of the strong gender")
public class IsStrongGenderRule {

    private static Logger LOGGER = LoggerFactory.getLogger(IsStrongGenderRule.class);

    private boolean success = false;

    @Condition
    public boolean when(@Fact("person") Person person) {

        LOGGER.debug("Evaluates using Gender: {}", person.getGender());

        if(isOfLegalSex(person.getGender())) {
            LOGGER.debug("Evaluated: true");
            return true;
        }

        LOGGER.debug("Evaluated: false ");
        return false;
    }

    @Action
    public void then() throws Exception {
        success = true;
    }

    public boolean isSuccess(){
        return success;
    }

    private boolean isOfLegalSex(Gender gender) {
        return Gender.MALE.equals(gender);
    }

    int getPriority() {
        return 2;
    }
}
