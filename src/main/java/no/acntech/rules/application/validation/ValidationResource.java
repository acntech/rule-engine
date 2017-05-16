package no.acntech.rules.application.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ValidationResource {

    private ValidationService validationService;

    @Autowired
    public ValidationResource(ValidationService validationService) {

        this.validationService = validationService;
    }

    @RequestMapping("/validation")
    public Boolean findAll() {
        return validationService.isLightWeightVehicle(3500);

    }

}
