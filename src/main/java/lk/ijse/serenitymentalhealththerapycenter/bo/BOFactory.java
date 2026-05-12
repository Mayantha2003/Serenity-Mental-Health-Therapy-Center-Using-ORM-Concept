package lk.ijse.serenitymentalhealththerapycenter.bo;

import lk.ijse.serenitymentalhealththerapycenter.bo.custom.impl.UserBOImpl;
import lk.ijse.serenitymentalhealththerapycenter.bo.custom.impl.PatientBOImpl;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory() {}

    public static BOFactory getInstance() {
        if (boFactory == null) {
            synchronized (BOFactory.class) {
                if (boFactory == null) {
                    boFactory = new BOFactory();
                }
            }
        }
        return boFactory;
    }

    @SuppressWarnings("unchecked")
    public <T extends SuperBO> T getBO(BOTypes boType) {
        return switch (boType) {
            case USER -> (T) new UserBOImpl();
            case PATIENT -> (T) new PatientBOImpl();
//            case THERAPIST -> (T) new TherapistBOImpl();
//            case THERAPY_SESSION -> (T) new TherapySessionBOImpl();
            default -> throw new IllegalArgumentException("Invalid BO Type: " + boType);
        };
    }
}