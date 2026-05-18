package lk.ijse.serenitymentalhealththerapycenter.config;

import lk.ijse.serenitymentalhealththerapycenter.entity.*;
import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Getter
public class SessionFactoryConfig {

    private static SessionFactoryConfig sessionFactoryConfig;
    private final SessionFactory sessionFactory;

    private SessionFactoryConfig() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        // Register all entity classes
        configuration.addAnnotatedClass(User.class);
//        configuration.addAnnotatedClass(Patient.class);
//        configuration.addAnnotatedClass(Therapist.class);
//        configuration.addAnnotatedClass(TherapyProgram.class);
//        configuration.addAnnotatedClass(TherapySession.class);
//        configuration.addAnnotatedClass(Payment.class);

        sessionFactory = configuration.buildSessionFactory();
    }

    public static SessionFactoryConfig getInstance() {
        if (sessionFactoryConfig == null) {
            sessionFactoryConfig = new SessionFactoryConfig();
        }
        return sessionFactoryConfig;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }

}