package no.acntech.rules.application.rules;

import no.acntech.rules.application.rules.basis.Gender;
import no.acntech.rules.application.rules.basis.Person;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class IsPersonLegalRegistreeCompositeRuleTest {

    private Person testPerson;

    @Before
    public void setup() {
        testPerson = new Person();
        testPerson.setBirthdate(LocalDate.of(1977, 6, 30));
        testPerson.setGender(Gender.MALE);
    }

    @Test
    public void testMaleAndOldEnoughPassTheValidation(){

        IsOldEnoughRule oldEnoughRule = new IsOldEnoughRule();
        IsLegalGenderRule strongGenderRule = new IsLegalGenderRule();

        IsPersonLegalRegistreeCompositeRule isPersonLegalRegistreeCompositeRule =
                new IsPersonLegalRegistreeCompositeRule();
        isPersonLegalRegistreeCompositeRule.addRule(oldEnoughRule);
        isPersonLegalRegistreeCompositeRule.addRule(strongGenderRule);

        Rules rules = new Rules();
        rules.register(isPersonLegalRegistreeCompositeRule);

        Facts facts = new Facts();
        facts.put("person", testPerson);

        assertThat(isPersonLegalRegistreeCompositeRule.evaluate(facts), is(true));
    }

    @Test
    public void testFemaleAndOldEnoughFailTheValidation(){

        IsOldEnoughRule oldEnoughRule = new IsOldEnoughRule();
        IsLegalGenderRule strongGenderRule = new IsLegalGenderRule();

        IsPersonLegalRegistreeCompositeRule isPersonLegalRegistreeCompositeRule =
                new IsPersonLegalRegistreeCompositeRule();
        isPersonLegalRegistreeCompositeRule.addRule(oldEnoughRule);
        isPersonLegalRegistreeCompositeRule.addRule(strongGenderRule);

        Rules rules = new Rules();
        rules.register(isPersonLegalRegistreeCompositeRule);

        Facts facts = new Facts();
        testPerson.setGender(Gender.FEMALE);
        facts.put("person", testPerson);

        assertThat(isPersonLegalRegistreeCompositeRule.evaluate(facts), is(false));
    }

    @Test
    public void testFemaleAndToYoungFailTheValidation(){

        IsOldEnoughRule oldEnoughRule = new IsOldEnoughRule();
        IsLegalGenderRule strongGenderRule = new IsLegalGenderRule();

        IsPersonLegalRegistreeCompositeRule isPersonLegalRegistreeCompositeRule =
                new IsPersonLegalRegistreeCompositeRule();
        isPersonLegalRegistreeCompositeRule.addRule(oldEnoughRule);
        isPersonLegalRegistreeCompositeRule.addRule(strongGenderRule);

        Rules rules = new Rules();
        rules.register(isPersonLegalRegistreeCompositeRule);

        Facts facts = new Facts();
        testPerson.setGender(Gender.FEMALE);
        testPerson.setBirthdate(LocalDate.now().minusYears(17));
        facts.put("person", testPerson);

        assertThat(isPersonLegalRegistreeCompositeRule.evaluate(facts), is(false));
    }

    @Test
    public void testMaleAndToYoungFailTheValidation(){

        IsOldEnoughRule oldEnoughRule = new IsOldEnoughRule();
        IsLegalGenderRule strongGenderRule = new IsLegalGenderRule();

        IsPersonLegalRegistreeCompositeRule isPersonLegalRegistreeCompositeRule =
                new IsPersonLegalRegistreeCompositeRule();
        isPersonLegalRegistreeCompositeRule.addRule(oldEnoughRule);
        isPersonLegalRegistreeCompositeRule.addRule(strongGenderRule);

        Rules rules = new Rules();
        rules.register(isPersonLegalRegistreeCompositeRule);

        Facts facts = new Facts();
        testPerson.setBirthdate(LocalDate.now().minusYears(17));
        facts.put("person", testPerson);

        assertThat(isPersonLegalRegistreeCompositeRule.evaluate(facts), is(false));
    }
}
