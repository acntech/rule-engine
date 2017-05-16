package no.acntech.rules.application.base.rule;

import java.util.function.Function;

public abstract class DerivationRule<T, R> implements Function<T, R> {

    public R apply(T t) {
        return utled(t);
    }

    public abstract R utled(T t);
}
