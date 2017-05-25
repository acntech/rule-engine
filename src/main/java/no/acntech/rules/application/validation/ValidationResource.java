package no.acntech.rules.application.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ValidationResource {

    private ValidationService validationService;

    @Autowired
    public ValidationResource(ValidationService validationService) {

        this.validationService = validationService;
    }

    @RequestMapping(path = "/legalRegistree/{birthdate}/{sex}", method = RequestMethod.GET)
    public Boolean isPersonLegalRegistree(@PathVariable String birthdate, @PathVariable String sex) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(birthdate, formatter);

        return validationService.isPersonALegalRegistree(date, sex);
    }
}
