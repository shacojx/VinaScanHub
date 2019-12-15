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
import Entity.VulnEntity;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;

public class VulnDao {

    SessionFactory factory = DBContent.getSessionFactory();

    public VulnDao() {

    }

    public void save(VulnEntity vuln) {
        if (null != vuln) {
            try {
                Session session = factory.openSession();
                Transaction tx = session.beginTransaction();
                session.save(vuln);
                tx.commit();
                session.clear();
                session.close();
            } catch (Exception ex) {
                throw new RuntimeException(ex.getMessage(), ex);
            }
        }
    }

    public void save(List<VulnEntity> vuln, History history) {
        if (null != vuln && vuln.size() > 0) {
            try {
                for (VulnEntity v : vuln) {
                    v.setHistoryId(history.getId());
                    this.save(v);
                }
            } catch (HibernateException ex) {
                throw new RuntimeException(ex.getMessage(), ex);
            }
        }
    }

    public List<VulnEntity> getListVulnEntity() {
        List<VulnEntity> list = new ArrayList<>();
        try {
            Session session = factory.openSession();
            list = (List<VulnEntity>) session.createQuery("from VulnEntity").list();
            session.clear();
            session.close();
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
        return list;
    }

    public List<VulnEntity> getListVulnEntityByHistoryId(Long id) {
        List<VulnEntity> list = new ArrayList<>();
        try {
            Session session = factory.openSession();
            String sql = "from VulnEntity where historyId = :id";
            Query query = session.createQuery(sql);
            query.setParameter("id", id);
            list = (List<VulnEntity>) query.list();
            session.clear();
            session.close();
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
        return list;
    }

    public void delete(VulnEntity vuln) {
        if (null != vuln) {
            try {
                Session session = factory.openSession();
                Transaction tx = session.beginTransaction();
                session.delete(vuln);
                tx.commit();
                session.clear();
                session.close();
            } catch (Exception ex) {
                throw new RuntimeException(ex.getMessage(), ex);
            }
        }
    }

}
