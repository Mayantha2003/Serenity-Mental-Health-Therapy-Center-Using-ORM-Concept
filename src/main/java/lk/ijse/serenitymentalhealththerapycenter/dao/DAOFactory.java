package lk.ijse.serenitymentalhealththerapycenter.dao;

import lk.ijse.serenitymentalhealththerapycenter.dao.custom.impl.UserDAOImpl;
import lk.ijse.serenitymentalhealththerapycenter.dao.custom.impl.PatientDAOImpl;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory() {}

    public static DAOFactory getInstance() {
        if (daoFactory == null) {
            synchronized (DAOFactory.class) {
                if (daoFactory == null) {
                    daoFactory = new DAOFactory();
                }
            }
        }
        return daoFactory;
    }

    @SuppressWarnings("unchecked")
    public <T extends SuperDAO> T getDAO(DAOTypes daoType) {
        return switch (daoType) {
            case USER -> (T) new UserDAOImpl();
            case PATIENT -> (T) new PatientDAOImpl();
//            case THERAPIST -> (T) new TherapistDAOImpl();
//            case THERAPY_SESSION -> (T) new TherapySessionDAOImpl();
            default -> throw new IllegalArgumentException("Invalid DAO Type: " + daoType);
        };
    }
}