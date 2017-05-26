package no.acntech.rules.application.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValidationResource {

    private ValidationService validationService;

    @Autowired
    public ValidationResource(ValidationService validationService) {

        this.validationService = validationService;
    }

    @RequestMapping(path = "/legalRegistree/{birthdate}/{sex}", method = RequestMethod.GET)
    public Boolean isPersonLegalRegistree(@PathVariable String birthdate, @PathVariable String sex) {

        return validationService.isPersonALegalRegistree(birthdate, sex);
    }

    @RequestMapping(path = "/oldEnough/{birthdate}", method = RequestMethod.GET)
    public Boolean isPersonOldEnough(@PathVariable String birthdate) {

        return validationService.isPersonOldEnoughToRegisterVehicle(birthdate);
    }

    @RequestMapping(path = "/legalGender/{gender}", method = RequestMethod.GET)
    public Boolean isPersonOfLegalGender(@PathVariable String gender) {

        return validationService.isPersonOfLegalGenderToRegisterVehicle(gender);
    }
}
