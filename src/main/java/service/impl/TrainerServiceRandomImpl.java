package service.impl;

import domain.Group;
import domain.Student;
import domain.Trainer;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.DomainRepository;
import service.ITrainerService;
import service.ServicesFactory;

import java.util.*;

/**
 * Implementare pentru ITrainerService care va genera valori random pentru nume/prenume si isAuthorized
 * Implementarea se va auto-inregistra la ServicesRegistration => nu expunem metoda pentru a returna instanta singleton (se auto-inregistreaza la ServicesRegistraion)
 */
@Log4j2
public class TrainerServiceRandomImpl extends ServiceRandomBaseImpl implements ITrainerService {
    private static final Logger log = LogManager.getLogger(ServicesFactory.class);

    private static final TrainerServiceRandomImpl theInstance = new TrainerServiceRandomImpl();

    public static ITrainerService getInstance() {
        return theInstance;
    }

    private TrainerServiceRandomImpl() {
        log.debug("TrainerServiceRandomImpl: IN:");

        // Instanta se auto-inregistreaza ca fiind implementarea ce o sa fie returnata de catre ServicesFactory
        ServicesFactory.getInstance().register(this);
    }

    @Override
    public Trainer getNewTrainer() {
        log.debug("getNewTrainer: IN");

        String firstName = generateFirstName();
        String lastName = generateLastName();
        boolean isAuthorized = randomUsed.nextBoolean();
        Trainer s = new Trainer(firstName, lastName, generateDateOrBirth(), isAuthorized);

        log.debug("getNewTrainer: OUT: " + s);

        return s;
    }

    @Override
    public Map<Trainer, Set<Student>> getAllTrainersWithStudentsMap() {
        log.debug("getAllTrainersWithStudentsMap(): IN");

        Map<Trainer, Set<Student>> allTrainersMap = new HashMap<>();
        List<Group> allGroups = DomainRepository.getInstance().getAllGroups();
        for (Group group: allGroups){
            Trainer trainer = group.getTrainer();
            List<Student> students = group.getStudents();
            if (!allTrainersMap.containsKey(trainer)){
                allTrainersMap.put(trainer, new HashSet<Student>());
            }
            allTrainersMap.get(trainer).addAll(students);
        }

        return allTrainersMap;

    }

}
