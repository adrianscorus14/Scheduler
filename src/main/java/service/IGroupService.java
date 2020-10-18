package service;



import domain.Group;
import domain.MaximumNumberOfStudentsReachedException;
import domain.Student;
import domain.Trainer;

import java.util.List;

public interface IGroupService {

    public Group buildNewGroup(List<Trainer> allTrainers, List<Student> allStudents) throws NoTrainerAvailableException, MaximumNumberOfStudentsReachedException;

    public Group getGroupWithMaxStudents();

    public List<Student> getAllYoungStudents();

}
