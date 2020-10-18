
import domain.Group;
import domain.Student;
import domain.Trainer;
import lombok.extern.log4j.Log4j2;
import service.*;
import service.impl.GroupServiceRandomImpl;
import service.impl.StudentServiceRandomImpl;
import service.impl.TrainerServiceRandomImpl;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Log4j2
public class Main {

    public static void main(String[] args) {

        TrainerServiceRandomImpl.getInstance();
        StudentServiceRandomImpl.getInstance();
        GroupServiceRandomImpl.getInstance();

        DomainRepository repository = DomainRepository.getInstance();

        IStudentService studentService = ServicesFactory.getInstance().getStudentService();
        ITrainerService trainerService = ServicesFactory.getInstance().getTrainerService();
        IGroupService groupService = ServicesFactory.getInstance().getGroupService();

        for (int i = 0; i < 15; i++) {
            repository.addStudent(studentService.getNewStudent());
        }

        for (int i = 0; i < 8; i++) {
            repository.addTrainer(trainerService.getNewTrainer());
        }

        for (int i = 0; i < 6; i++) {
            try {
                repository.addGroup(groupService.buildNewGroup(repository.getAllTrainers(), repository.getAllStudents()));
            } catch (NoTrainerAvailableException e) {
                log.error("NoTrainerAvailableException: " + e.getMessage());
            } catch (MaximumNumberOfStudentsReachedException e) {
                log.error("MaximumNumberOfStudentsReachedException: " + e.getMessage());
            }
        }

        log.info("Groups:");
        repository.getAllGroups().stream().forEach(g -> log.info(g));

        displayStudentsAlphabetically();

        Group bigGroup = groupService.getGroupWithMaxStudents();
        log.info("The group with biggest number of Students " + bigGroup);


        List<Student> youngStudents = groupService.getAllYoungStudents();
        log.info("The following students are young " + youngStudents );

        Map<Trainer, Set<Student>> allTrainers = trainerService.getAllTrainersWithStudentsMap();
        log.info("all Trainers with students: " + allTrainers);

        allTrainers.entrySet().stream().forEach(trainerSetEntry -> {
            log.info("Trainer: " + trainerSetEntry.getKey());
            trainerSetEntry.getValue().stream().forEach(student -> log.info("\t" + student));
        });
    }

    private static void displayStudentsAlphabetically() {
        DomainRepository repository = DomainRepository.getInstance();

        if (!repository.getAllGroups().isEmpty()) {

            log.info("Students sorted Alphabetically");

            Group grupaStudenti = repository.getAllGroups().get(0);

            grupaStudenti.getStudents().stream().sorted(new Comparator<Student>() {
                @Override
                public int compare(Student s1, Student s2) {
                    return s1.getLastName().compareToIgnoreCase(s2.getLastName());
                }
            }).forEach(student -> log.info(student));
        }
    }


}
