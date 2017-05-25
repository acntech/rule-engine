package no.acntech.rules.application.rules;

import no.acntech.rules.application.rules.basis.Person;
import no.acntech.rules.application.rules.basis.Sex;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Rule(name = "IsTheStrongGenderRule", description = "Checks if the person is of the strong gender")
public class IsTheStrongGenderRule {

    private static Logger LOGGER = LoggerFactory.getLogger(IsTheStrongGenderRule.class);

    private boolean success = false;

    @Condition
    public boolean when(@Fact("person") Person person) {

        LOGGER.debug("Evaluates using Sex: {}", person.getSex());

        if(isOfLegalSex(person.getSex())) {
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

    boolean isSuccess(){
        return success;
    }

    private boolean isOfLegalSex(Sex sex) {
        return Sex.MALE.equals(sex);
    }

    int getPriority() {
        return 2;
    }
}
