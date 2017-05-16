package no.acntech.rules.application.base.rule;

import no.acntech.rules.application.base.basis.Basis;

import java.util.function.Predicate;

public abstract class Rule<T extends Basis> implements Predicate<T> {

    public boolean test(T t) {
        return validate(t);
    }

    public abstract boolean validate(T t);
}
