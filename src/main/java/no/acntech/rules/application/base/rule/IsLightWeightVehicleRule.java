package no.acntech.rules.application.base.rule;

import no.acntech.rules.application.base.basis.WeightBasis;

public class IsLightWeightVehicleRule extends Rule<WeightBasis>{

    public boolean validate(WeightBasis basis){

        return basis.getWeightInKg() <= 3500 ? true : false;
    }
}
