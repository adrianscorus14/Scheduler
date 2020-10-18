package service.impl;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.ServicesFactory;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Random;

/**
 * Clasa comuna pentru implementarile de servicii care ofera valori random pentru campurile entitatilor
 * Contine partile comune implementarilor
 */
@Log4j2
public abstract class ServiceRandomBaseImpl {
    private static final Logger log = LogManager.getLogger(ServicesFactory.class);

    protected Random randomUsed = new Random();

    protected ServiceRandomBaseImpl() {
        log.debug("ServiceRandomBaseImpl: IN");
    }

    protected String generateFirstName() {
        return RandomStringUtils.random(7, true, false);
    }

    protected String generateLastName() {
        return RandomStringUtils.random(10, true, false);
    }

    /**
     * Genereaza data nasterii plecand de la data curente,
     * folosind un random pentru varsta si scazand din data curenta varsta studentului
     *
     * @return
     */
    protected Date generateDateOrBirth() {
        log.debug("generateDateOrBirth: IN:");
        int age = 0;
        while (age < 15) {
            age = randomUsed.nextInt(60);
        }
        log.debug("Computed Age " + age);

        Date dateOrBirth = Date.from(LocalDate.now().minusYears(age).atStartOfDay().toInstant(ZoneOffset.UTC));

        log.debug("generateDateOrBirth: OUT:" + dateOrBirth);

        return dateOrBirth;
    }

}
