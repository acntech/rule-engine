package no.acntech.rules.application.validation;

import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ValidationServiceTest {

    @Test
    public void testOldEnoughMalePassValidation() {
        ValidationService service = new ValidationService();

        assertThat(service.isPersonALegalRegistree(LocalDate.of(1977, 6, 30), "male"), is(true));
    }

    @Test
    public void testOldEnoughFemaleFailValidation() {
        ValidationService service = new ValidationService();

        assertThat(service.isPersonALegalRegistree(LocalDate.of(1977, 6, 30), "female"), is(false));
    }

    @Test
    public void testTooYoungFemaleFailValidation() {
        ValidationService service = new ValidationService();

        assertThat(service.isPersonALegalRegistree(LocalDate.now().minusYears(17), "female"), is(false));
    }

    @Test
    public void testTooYoungMaleFailValidation() {
        ValidationService service = new ValidationService();

        assertThat(service.isPersonALegalRegistree(LocalDate.now().minusYears(17), "male"), is(false));
    }
}
