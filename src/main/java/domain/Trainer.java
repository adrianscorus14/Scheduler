package domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Trainer extends Person {

    private final boolean isAuthorized;

    public Trainer(String firstName, String lastName, Date dateOfBirth, boolean isAuthorized) {
        super(firstName, lastName, dateOfBirth);
        this.isAuthorized = isAuthorized;
    }

    @Override
    public boolean isAuthorized() {
        return false;
    }
}
