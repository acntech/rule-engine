package no.acntech.rules.application.rules.basis;

import java.time.LocalDate;

public class Person {

    private Gender gender;
    private LocalDate birthdate;


    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }
}
