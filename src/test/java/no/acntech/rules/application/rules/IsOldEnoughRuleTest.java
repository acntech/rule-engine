package no.acntech.rules.application.rules;

import no.acntech.rules.application.rules.basis.Person;
import org.jeasy.rules.api.*;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.jeasy.rules.core.RulesEngineBuilder.aNewRulesEngine;

public class IsOldEnoughRuleTest {

    private Person testPerson;

    @Test
    public void testWayTooOldPassTheValidation() {

        IsOldEnoughRule rule = new IsOldEnoughRule();

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
        assertThat(rule.getPriority(), is(1));
    }

    @Test
    public void testEighteenYearsOldPassTheValidation() {

        IsOldEnoughRule rule = new IsOldEnoughRule();

        Facts facts = new Facts();
        testPerson.setBirthdate(LocalDate.now().minusYears(18));
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
        assertThat(rule.getPriority(), is(1));
    }

    @Test
    public void testSeventeenYearsAnd364DaysOldFailTheValidation() {

        IsOldEnoughRule rule = new IsOldEnoughRule();

        Facts facts = new Facts();
        testPerson.setBirthdate(LocalDate.now().minusYears(17).plusDays(364));
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
        assertThat(rule.getPriority(), is(1));
    }

    @Before
    public void setup() {
        testPerson = new Person();
        testPerson.setBirthdate(LocalDate.of(1977, 6, 30));
    }
}