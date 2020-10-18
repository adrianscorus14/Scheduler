package service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Factory pentru serviciile folosite in aplicatie
 * Foloseste singleton design-pattern pentru a oferi o singura instanta catre consumatori
 * Implementeaza ServicesRegistration pentru a oferi o modalitate de a inregistra implementari specifice pentru serviciile oferite
 */
public class ServicesFactory implements ServicesRegistration {

    private static final Logger log = LogManager.getLogger(ServicesFactory.class);

    private static final ServicesFactory THE_INSTANCE = new ServicesFactory();

    private IStudentService studentService;
    private ITrainerService trainerService;
    private IGroupService groupService;

    public static ServicesFactory getInstance(){
        return THE_INSTANCE;
    }

    private ServicesFactory() {
    }

    public IStudentService getStudentService() {
        log.debug("getStudentService: IN: ");

        return studentService;
    }

    public ITrainerService getTrainerService() {
        log.debug("getTrainerService: IN: ");

        return trainerService;
    }

    public IGroupService getGroupService() {
        log.debug("getGroupService: IN: ");

        return groupService;
    }

    @Override
    public void register(IStudentService studentService) {
        log.debug("register: IN: " + studentService.getClass().getName());

        this.studentService = studentService;
    }

    @Override
    public void register(ITrainerService trainerService) {
        log.debug("register: IN: " + trainerService.getClass().getName());

        this.trainerService = trainerService;
    }

    @Override
    public void register(IGroupService groupService) {
        log.debug("register: IN: " + groupService.getClass().getName());

        this.groupService = groupService;
    }

}
