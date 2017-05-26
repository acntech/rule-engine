package no.acntech.rules.application.rules;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.core.CompositeRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Iterator;


@Component
public class IsPersonLegalRegistreeCompositeRule extends CompositeRule {

    private static Logger LOGGER = LoggerFactory.getLogger(IsPersonLegalRegistreeCompositeRule.class);

    public IsPersonLegalRegistreeCompositeRule() {
        super("IsPersonLegalRegistreeCompositeRule", "Composite of rules deciding if a person is a legal registree");
        addRule(new IsOldEnoughRule());
        addRule(new IsStrongGenderRule());
    }

    public boolean evaluate(Facts facts) {
        LOGGER.trace("Evaluating composite rule using the following facts: {}", facts);
        Iterator ruleIterator = this.rules.iterator();

        Rule rule;
        do {
            if (!ruleIterator.hasNext()) {
                LOGGER.debug("Evaluated true because all {} composites has evaluated true", rules.size());
                return true;
            }

            rule = (Rule) ruleIterator.next();
        } while (rule.evaluate(facts));

        LOGGER.debug("Evaluated false because of the composite {} has evaluated false", rule.getName());
        return false;

    }


    @Override
    public int getPriority() {
        return 0;
    }

}
