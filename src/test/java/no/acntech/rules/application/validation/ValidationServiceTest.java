package no.acntech.rules.application.validation;

import no.acntech.rules.application.rules.IsPersonLegalRegistreeCompositeRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;


@RunWith(SpringJUnit4ClassRunner.class)
public class ValidationServiceTest {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Mock
    private IsPersonLegalRegistreeCompositeRule isPersonLegalRegistreeCompositeRule;

    @InjectMocks
    private ValidationService service;

    @Test
    public void testPassedCompositeValidation() {
        when(isPersonLegalRegistreeCompositeRule.evaluate(any())).thenReturn(true);

        assertThat(service.isPersonALegalRegistree(LocalDate.now().format(formatter), "male"), is(true));
    }

    @Test
    public void testFailedCompositeValidation() {
        when(isPersonLegalRegistreeCompositeRule.evaluate(any())).thenReturn(false);
        assertThat(service.isPersonALegalRegistree(LocalDate.now().format(formatter), "female"), is(false));
    }


    @Test
    public void testPassedAgeValidation() {
        assertThat(service.isPersonOldEnoughToRegisterVehicle(LocalDate.of(1970, 1, 15).format(formatter)), is(true));
    }

    @Test
    public void testFailedAgeValidation() {
        assertThat(service.isPersonOldEnoughToRegisterVehicle(LocalDate.of(2015, 2, 16).format(formatter)), is(false));
    }

    @Test
    public void testPassedSexValidation() {

        assertThat(service.isPersonOfLegalGenderToRegisterVehicle("male"), is(true));
    }

    @Test
    public void testFailedSexValidation() {
        assertThat(service.isPersonOfLegalGenderToRegisterVehicle("female"), is(false));
    }

}
