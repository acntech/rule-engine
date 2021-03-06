package no.acntech.rules.application.rules;

import no.acntech.rules.application.rules.basis.Person;
import no.acntech.rules.application.rules.basis.Gender;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.jeasy.rules.core.RulesEngineBuilder.aNewRulesEngine;

public class IsLegalGenderRuleTest {

    private Person testPerson;

    @Before
    public void setup() {
        testPerson = new Person();
        testPerson.setGender(Gender.MALE);
    }

    @Test
    public void testMalePassTheValidation(){
        IsLegalGenderRule rule = new IsLegalGenderRule();

        Facts facts = new Facts();
        facts.put("person", testPerson);

        Rules rules = new Rules();
        rules.register(rule);

        RulesEngine rulesEngine = aNewRulesEngine()
                .withSkipOnFirstFailedRule(true)
                .withSkipOnFirstAppliedRule(true)
                .withSilentMode(true)
                .build();
        rulesEngine.fire(rules, facts);

        assertThat(rule.isSuccess(), is(true));
        assertThat(rule.getPriority(), is(2));
    }

    @Test
    public void testFemaleFailTheValidation(){
        IsLegalGenderRule rule = new IsLegalGenderRule();

        testPerson.setGender(Gender.FEMALE);

        Facts facts = new Facts();
        facts.put("person", testPerson);

        Rules rules = new Rules();
        rules.register(rule);

        RulesEngine rulesEngine = aNewRulesEngine()
                .withSkipOnFirstFailedRule(true)
                .withSkipOnFirstAppliedRule(true)
                .withSilentMode(true)
                .build();
        rulesEngine.fire(rules, facts);

        assertThat(rule.isSuccess(), is(false));
        assertThat(rule.getPriority(), is(2));
    }
}
