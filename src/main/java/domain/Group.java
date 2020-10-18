package domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@EqualsAndHashCode
@ToString
public class Group implements Comparable<Group> {

    private final int MAX_NR_STUDENTS = 10;

    @Getter
    private final String name;
    @Getter
    private final Trainer trainer;
    private final List<Student> students = new ArrayList<>();

    public Group(String name, Trainer trainer) {
        this.name = name;
        this.trainer = trainer;
    }

    public void addStudent(Student s) throws MaximumNumberOfStudentsReachedException {
        if (students.size() == MAX_NR_STUDENTS) {
            throw new MaximumNumberOfStudentsReachedException("Max number of students already reached: " + MAX_NR_STUDENTS);
        }
        students.add(s);
    }

    public List<Student> getStudents() {
        return Collections.unmodifiableList(students);
    }

    @Override
    public int compareTo(Group group) {
        return  group.students.size() - students.size();
    }


    public Trainer getTrainer() {
        return trainer;
    }
}
