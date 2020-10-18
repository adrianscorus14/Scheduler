package domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@EqualsAndHashCode
@Getter
@ToString
public abstract class Person {

    private final String firstName;
    private final String lastName;
    private final Date dateOfBirth;

    public Person(String firstName, String lastName, Date dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        Date currentDate = new Date();
        return (int) ((((((currentDate.getTime() - dateOfBirth.getTime()) / 1000) / 60) / 60) / 24) / 365);
    }

    public abstract boolean isAuthorized();
}
