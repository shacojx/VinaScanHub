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

public class HistoryDao {

    SessionFactory factory = DBContent.getSessionFactory();

    public HistoryDao() {

    }

    public void save(History history) {
        if (null != history) {
            try {
                Session session = factory.openSession();
                Transaction tx = session.beginTransaction();
                session.save(history);
                tx.commit();
                session.clear();
                session.close();
            } catch (Exception ex) {
                throw new RuntimeException(ex.getMessage(), ex);
            }
        }
    }

    public void save(List<History> history) {
        if (null != history && history.size() > 0) {
            try {
                Session session = factory.openSession();
                Transaction tx = session.beginTransaction();

                int i = 0;
                history.forEach((History _item) -> {
                    session.persist(_item);
                    tx.commit();
                });
                session.clear();
                session.close();
            } catch (Exception ex) {
                throw new RuntimeException(ex.getMessage(), ex);
            }
        }
    }

    public List<History> getListHistory() {
        List<History> list = new ArrayList<>();
        try {
            Session session = factory.openSession();
            list = (List<History>) session.createQuery("from History").list();
            session.clear();
            session.close();
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
        return list;
    }

    public History getHistoryByID(History history) {
        if (null != history) {
            try {
                Session session = factory.openSession();
                Transaction tx = session.beginTransaction();
                String sql = "from History";
                History result = (History) session.createQuery(sql).uniqueResult();
                tx.commit();
                session.clear();
                session.close();
                return null;
            } catch (Exception ex) {
                throw new RuntimeException(ex.getMessage(), ex);
            }
        }
        return null;
    }

    public void delete(History history) {
        if (null != history) {
            try {
                Session session = factory.openSession();
                Transaction tx = session.beginTransaction();
                session.delete(history);
                tx.commit();
                session.clear();
                session.close();
            } catch (Exception ex) {
                throw new RuntimeException(ex.getMessage(), ex);
            }
        }
    }

}
