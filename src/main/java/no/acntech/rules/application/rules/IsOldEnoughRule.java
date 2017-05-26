package no.acntech.rules.application.rules;

import no.acntech.rules.application.rules.basis.Person;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

@Rule(name = "IsOldEnoughRule", description = "Checks that a client is old enough to register a car")
public class IsOldEnoughRule {

    private static Logger LOGGER = LoggerFactory.getLogger(IsOldEnoughRule.class);

    private boolean success = false;

    @Condition
    public boolean when(@Fact("person") Person person) {

        LOGGER.debug("Evaluates using birthdate: {}", person.getBirthdate());
        if(isOfLegalAge(person.getBirthdate()) ){
            LOGGER.debug("Evaluated: true");
            return true;
        }
        LOGGER.debug("Evaluated: false");
        return false;
    }

    @Action
    public void then() throws Exception {
        success = true;
    }

    public boolean isSuccess(){
        return success;
    }

    int getPriority() {
        return 1;
    }

    private boolean isOfLegalAge(LocalDate birthDate) {
        return (LocalDate.now().minusYears(18).isAfter(birthDate) || LocalDate.now().minusYears(18).isEqual(birthDate));
    }

}
