package service.impl;

import domain.Group;
import domain.MaximumNumberOfStudentsReachedException;
import domain.Student;
import domain.Trainer;
import lombok.extern.log4j.Log4j2;
import service.DomainRepository;
import service.IGroupService;
import service.NoTrainerAvailableException;
import service.ServicesFactory;

import java.util.List;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Log4j2
public class GroupServiceRandomImpl extends ServiceRandomBaseImpl implements IGroupService {

    private static final GroupServiceRandomImpl theInstance = new GroupServiceRandomImpl();
    private static final String PREFIX = "groupName_";
    private static final Logger log = LogManager.getLogger(ServicesFactory.class);

    private int counter = 0;

    public static IGroupService getInstance() {
        return theInstance;
    }

    private GroupServiceRandomImpl() {
        log.debug("GroupServiceRandomImpl: IN:");

        // Instanta se auto-inregistreaza ca fiind implementarea ce o sa fie returnata de catre ServicesFactory
        ServicesFactory.getInstance().register(this);
    }

    @Override
    public Group buildNewGroup(List<Trainer> allTrainers, List<Student> allStudents) throws NoTrainerAvailableException, MaximumNumberOfStudentsReachedException {
        log.debug("buildNewGroup(): IN");

        String groupName = PREFIX + counter++;
        Trainer trainer = getRandomTrainer(allTrainers);
        Group g = new Group(groupName, trainer);

        int nrStudents = randomUsed.nextInt(9);
        for (int i = 0; i < nrStudents; i++) {
            log.debug("New Student to be added: " + i);
            Student student = allStudents.get(randomUsed.nextInt(allStudents.size()));
            if (!g.getStudents().contains(student)) {
                g.addStudent(student);
                log.debug("New Student added: " + student);
            }
        }

        log.debug("buildNewGroup(): OUT: " + g);
        return g;
    }

    public Group getGroupWithMaxStudents() {
        return DomainRepository.getInstance().getAllGroups().stream().sorted().collect(Collectors.toList()).get(0);
    }

    @Override
    public List<Student> getAllYoungStudents() {
        return DomainRepository.getInstance().getAllGroups().stream()
                .flatMap(group -> group.getStudents().stream())
                .filter(student -> student.getAge() < 25)
                .collect(Collectors.toList());
    }

    private Trainer getRandomTrainer(List<Trainer> allTrainers) throws NoTrainerAvailableException {
        log.debug("getRandomTrainer: IN: ");

        if (null == allTrainers || allTrainers.isEmpty()) {
            throw new NoTrainerAvailableException("No trainer available!");
        }

        List<Trainer> authorizedTrainers = allTrainers.stream()
                .filter(t -> t.isAuthorized())
                .collect(Collectors.toList());
        if (authorizedTrainers.isEmpty()) {
            throw new NoTrainerAvailableException("No authorized trainer available!");
        }

        Trainer t = authorizedTrainers.get(randomUsed.nextInt(authorizedTrainers.size()));

        log.debug("getRandomTrainer: OUT: " + t);
        return t;
    }
}
