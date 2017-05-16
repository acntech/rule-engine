package no.acntech.rules.application.validation;

import no.acntech.rules.application.base.basis.WeightBasis;
import no.acntech.rules.application.base.rule.IsLightWeightVehicleRule;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    public boolean isLightWeightVehicle(long weightInKg){

        WeightBasis basis = new WeightBasis();
        basis.setWeightInKg(3500);

        IsLightWeightVehicleRule regel = new IsLightWeightVehicleRule();
        return regel.validate(basis);
    }

}
