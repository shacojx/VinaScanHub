/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBContent;

import Entity.History;
import Entity.VulnEntity;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 *
 * @author Shaco JX
 */
public class DBContent {

    private static SessionFactory sessionFactory;// = buildSessionFactory();

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                // loads configuration and mappings
                disableLogging();
                Configuration configuration = new Configuration().configure();
                ServiceRegistry serviceRegistry
                        = new StandardServiceRegistryBuilder()
                                .applySettings(configuration.getProperties()).build();

                configuration.addAnnotatedClass(History.class);
                configuration.addAnnotatedClass(VulnEntity.class);

                // builds a session factory from the service registry
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (HibernateException ex) {

            }
        }
        return sessionFactory;
    }

    private static void disableLogging() {
        LogManager logManager = LogManager.getLogManager();
        Logger logger = logManager.getLogger("");
        logger.setLevel(Level.OFF); //could be Level.OFF
    }
}
