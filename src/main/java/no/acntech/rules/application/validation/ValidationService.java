package no.acntech.rules.application.validation;

import no.acntech.rules.application.rules.IsOldEnoughRule;
import no.acntech.rules.application.rules.IsPersonLegalRegistreeCompositeRule;
import no.acntech.rules.application.rules.IsTheStrongGenderRule;
import no.acntech.rules.application.rules.basis.Person;
import no.acntech.rules.application.rules.basis.Sex;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ValidationService {

    public boolean isPersonALegalRegistree(LocalDate birthDate, String sex){
        Person person = new Person();
        person.setBirthdate(birthDate);
        if("male".equalsIgnoreCase(sex)) {
            person.setSex(Sex.MALE);
        } else {
            person.setSex(Sex.FEMALE);
        }

        IsOldEnoughRule oldEnoughRule = new IsOldEnoughRule();
        IsTheStrongGenderRule strongGenderRule = new IsTheStrongGenderRule();

        IsPersonLegalRegistreeCompositeRule isPersonLegalRegistreeCompositeRule =
                new IsPersonLegalRegistreeCompositeRule("IsPersonLegalRegistreeCompositeRule", "Composite of rules deciding if a person is a legal registree");
        isPersonLegalRegistreeCompositeRule.addRule(oldEnoughRule);
        isPersonLegalRegistreeCompositeRule.addRule(strongGenderRule);

        Rules rules = new Rules();
        rules.register(isPersonLegalRegistreeCompositeRule);

        Facts facts = new Facts();
        facts.put("person", person);

        return isPersonLegalRegistreeCompositeRule.evaluate(facts);
    }

}
