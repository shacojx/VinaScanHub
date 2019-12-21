/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBContent;

/**
 *
 * @author hunternight
 */
import Entity.History;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;

public class HistoryDao {

    SessionFactory factory;

    public HistoryDao() {
        try{
            factory = DBContent.getSessionFactory();
        }catch(Exception ex){
            factory = DBContent.getSessionFactory();
        }
    }

    public void save(History history) {
        if (null != history) {
            try {
                try (Session session = factory.openSession()) {
                    Transaction tx = session.beginTransaction();
                    session.save(history);
                    tx.commit();
                    session.clear();
                }
            } catch (HibernateException ex) {
                throw new RuntimeException(ex.getMessage(), ex);
            }
        }
    }

    public List<History> getListHistory() {
        List<History> list = new ArrayList<>();
        try {
            try (Session session = factory.openSession()) {
                list = (List<History>) session.createQuery("from History").list();
                session.clear();
            }
        } catch (HibernateException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
        return list;
    }

    public List<History> getListHistoryById(Long id) {
        List<History> list = new ArrayList<>();
        try {
            try (Session session = factory.openSession()) {
                String sql = "from History where id = :id";
                Query query = session.createQuery(sql);
                query.setParameter("id", id);
                list = (List<History>) query.list();
                session.clear();
            }
        } catch (HibernateException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
        return list;
    }

    public void delete(History history) {
        if (null != history) {
            try {
                try (Session session = factory.openSession()) {
                    Transaction tx = session.beginTransaction();
                    session.delete(history);
                    tx.commit();
                    session.clear();
                }
            } catch (HibernateException ex) {
                throw new RuntimeException(ex.getMessage(), ex);
            }
        }
    }
}
