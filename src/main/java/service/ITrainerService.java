package service;


import domain.Student;
import domain.Trainer;

import java.util.Map;
import java.util.Set;

public interface ITrainerService {

    public Trainer getNewTrainer();
    public Map<Trainer, Set<Student>> getAllTrainersWithStudentsMap ();

}
