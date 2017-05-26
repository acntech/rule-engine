package no.acntech.rules.application.validation;

import no.acntech.rules.application.rules.IsOldEnoughRule;
import no.acntech.rules.application.rules.IsPersonLegalRegistreeCompositeRule;
import no.acntech.rules.application.rules.IsLegalGenderRule;
import no.acntech.rules.application.rules.basis.Person;
import no.acntech.rules.application.rules.basis.Gender;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.jeasy.rules.core.RulesEngineBuilder.aNewRulesEngine;

@Service
public class ValidationService {

    @Autowired
    private IsPersonLegalRegistreeCompositeRule isPersonLegalRegistreeCompositeRule;

    /**
     * Example on how to use a compositerule, consisting of two composites
     * @param birthDateString A text representing a birthdate in the format "2005-03-23"
     * @param sex The sex of the person registering a vehicle "male" or "female"
     * @return Returns true if this is a legal combination for registering a vehicle, else false
     */
    boolean isPersonALegalRegistree(String birthDateString, String sex){

        Person person = new Person();
        parseAndInsertGenderString(sex, person);
        parseAndInsertBirthDate(birthDateString, person);

        Facts facts = new Facts();
        facts.put("person", person);

        return isPersonLegalRegistreeCompositeRule.evaluate(facts);
    }

    /**
     * Example on how to use a simple rule
     * @param birthDateString A text representing a birthdate in the format "2005-03-23"
     * @return Returns true if this is a person that is old enough to registering a vehicle, else false
     */
    boolean isPersonOldEnoughToRegisterVehicle(String birthDateString){

        IsOldEnoughRule isOldEnoughRule = new IsOldEnoughRule();
        Person person = new Person();
        parseAndInsertBirthDate(birthDateString, person);

        System.out.println("Dato: " + person.getBirthdate().toString());

        Facts facts = new Facts();
        facts.put("person", person);

        Rules rules = new Rules();
        rules.register(isOldEnoughRule);

        RulesEngine rulesEngine = aNewRulesEngine()
                .withSkipOnFirstFailedRule(true)
                .withSilentMode(true)
                .build();
        rulesEngine.fire(rules, facts);

        return isOldEnoughRule.isSuccess();
    }

    /**
     * Example on how to use a simple rule
     * @param gender The sex of the person registering a vehicle "male" or "female"
     * @return Returns true if this is a male, false if it is a female
     */
    boolean isPersonOfLegalGenderToRegisterVehicle(String gender){

        IsLegalGenderRule isLegalGenderRule = new IsLegalGenderRule();
        Person person = new Person();
        parseAndInsertGenderString(gender, person);

        Facts facts = new Facts();
        facts.put("person", person);

        Rules rules = new Rules();
        rules.register(isLegalGenderRule);

        RulesEngine rulesEngine = aNewRulesEngine()
                .withSkipOnFirstFailedRule(true)
                .withSilentMode(true)
                .build();
        rulesEngine.fire(rules, facts);

        return isLegalGenderRule.isSuccess();
    }

    private void parseAndInsertGenderString(final String text, Person person) {
        if("male".equalsIgnoreCase(text)){
            person.setGender(Gender.MALE);
        } else {
            person.setGender(Gender.FEMALE);
        }
    }

    private void parseAndInsertBirthDate(final String birthDateString, Person person) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthdate = LocalDate.parse(birthDateString, formatter);

        person.setBirthdate(birthdate);
    }

}
