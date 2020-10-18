package service;


import domain.Group;
import domain.Student;
import domain.Trainer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DomainRepository {

    private static final DomainRepository theInstance = new DomainRepository();

    private final List<Student> allStudents = new ArrayList<>();
    private final List<Trainer> allTrainers = new ArrayList<>();
    private final List<Group> allGroups = new ArrayList<>();

    public static DomainRepository getInstance() {
        return theInstance;
    }

    private DomainRepository(){
    }

    public List<Student> getAllStudents() {
        return Collections.unmodifiableList(allStudents);
    }

    public List<Trainer> getAllTrainers() {
        return Collections.unmodifiableList(allTrainers);
    }

    public List<Group> getAllGroups() {
        return Collections.unmodifiableList(allGroups);
    }

    public void addStudent(Student s){
        allStudents.add(s);
    }

    public void addTrainer(Trainer t) {
        allTrainers.add(t);
    }

    public void addGroup(Group g) {
        allGroups.add(g);
    }

}
