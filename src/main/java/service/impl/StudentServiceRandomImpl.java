package service.impl;


import domain.Student;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.IStudentService;
import service.ServicesFactory;

/**
 * Implementare pentru IStudentService care va genera valori random pentru nume/prenume si hasPreviousJavaKnowledge
 * Implementarea se va auto-inregistra la ServicesRegistration => nu expunem metoda pentru a returna instanta singleton (se auto-inregistreaza la ServicesRegistraion)
 */
@Log4j2
public class StudentServiceRandomImpl extends ServiceRandomBaseImpl implements IStudentService {
    private static final Logger log = LogManager.getLogger(ServicesFactory.class);

    private static final StudentServiceRandomImpl theInstance = new StudentServiceRandomImpl();

    public static IStudentService getInstance() {
        return theInstance;
    }

    private StudentServiceRandomImpl() {
        log.debug("StudentServiceRandomImpl: IN:");

        // Instanta se auto-inregistreaza ca fiind implementarea ce o sa fie returnata de catre ServicesFactory
        ServicesFactory.getInstance().register(this);
    }

    @Override
    public Student getNewStudent() {
        log.debug("getNewStudent: IN");

        String firstName = generateFirstName();
        String lastName = generateLastName();
        boolean hasPreviousJavaKnowledge = randomUsed.nextBoolean();
        Student s = new Student(firstName, lastName, generateDateOrBirth(), hasPreviousJavaKnowledge);

        log.debug("getNewStudent: OUT: " + s);

        return s;
    }

}
