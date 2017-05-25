package no.acntech.rules.application.rules;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.core.CompositeRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;


public class IsPersonLegalRegistreeCompositeRule extends CompositeRule {

    private static Logger LOGGER = LoggerFactory.getLogger(IsPersonLegalRegistreeCompositeRule.class);

    public IsPersonLegalRegistreeCompositeRule(String name, String description){
        super(name, description);
    }

    public boolean evaluate(Facts facts) {
        LOGGER.trace("Evaluating composite rule using the following facts: {}", facts);
        if(!this.rules.isEmpty()) {
            Iterator ruleIterator = this.rules.iterator();

            Rule rule;
            do {
                if(!ruleIterator.hasNext()) {
                    LOGGER.debug("Evaluated true because all {} composites has evaluated true", rules.size());
                    return true;
                }

                rule = (Rule)ruleIterator.next();
            } while(rule.evaluate(facts));

            LOGGER.debug("Evaluated false because of the composite {} has evaluated false", rule.getName());
            return false;
        } else {
            LOGGER.debug("Evaluated false because of missing composites");
            return false;
        }
    }



    @Override
    public int getPriority() {
        return 0;
    }

}
