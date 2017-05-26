package no.acntech.rules.application.rules;

import no.acntech.rules.application.rules.basis.Gender;
import no.acntech.rules.application.rules.basis.Person;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Rule(name = "IsLegalGenderRule", description = "Checks if the person is of the strong gender")
public class IsLegalGenderRule {

    private static Logger LOGGER = LoggerFactory.getLogger(IsLegalGenderRule.class);

    private boolean success = false;

    @Condition
    public boolean when(@Fact("person") Person person) {

        LOGGER.debug("Evaluates using Gender: {}", person.getGender());

        if(isOfLegalGender(person.getGender())) {
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

    private boolean isOfLegalGender(Gender gender) {
        return Gender.MALE.equals(gender);
    }

    int getPriority() {
        return 2;
    }
}
