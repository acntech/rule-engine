package no.acntech.rules.application.validation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
public class ValidationResourceTest {

    @Mock
    ValidationService validationService;

    @InjectMocks
    ValidationResource resource;

    @Test
    public void testValidationResource() {
        //ValidationResource resource = new ValidationResource(validationService);
        when(validationService.isPersonALegalRegistree(any(), any())).thenReturn(true);

        assertThat(resource.isPersonLegalRegistree("1977-06-30", "male"), is(true));
        verify(validationService).isPersonALegalRegistree(any(), any());
    }
}

