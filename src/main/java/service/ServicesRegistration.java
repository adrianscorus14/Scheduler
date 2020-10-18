package service;

/**
 * Interfata care ofera metode pentru a inregistra servicii,
 * fara a cunoaste detalii despre implementarile specifice interfetelor respective
 */
public interface ServicesRegistration {

    public void register(IStudentService studentService);
    public void register(ITrainerService trainerService);
    public void register(IGroupService groupService);

}
