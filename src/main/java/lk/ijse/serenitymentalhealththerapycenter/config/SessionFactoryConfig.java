package lk.ijse.serenitymentalhealththerapycenter.config;

import lk.ijse.serenitymentalhealththerapycenter.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryConfig {

    private static SessionFactoryConfig sessionFactoryConfig;
    private final SessionFactory sessionFactory;

    private SessionFactoryConfig() {
        Configuration configuration = new Configuration().configure();
//                .addAnnotatedClass(User.class)
//                .addAnnotatedClass(Therapist.class)
//                .addAnnotatedClass(TherapyProgram.class)
//                .addAnnotatedClass(Patient.class)
//                .addAnnotatedClass(TherapySession.class)
//                .addAnnotatedClass(Payment.class);

        sessionFactory = configuration.buildSessionFactory();
    }

    public static SessionFactoryConfig getInstance() {
        return (sessionFactoryConfig == null)
                ? sessionFactoryConfig = new SessionFactoryConfig()
                : sessionFactoryConfig;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}