package no.acntech.rules.application.rules.basis;

import java.time.LocalDate;

public class Person {

    private Sex sex;
    private LocalDate birthdate;


    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }
}
