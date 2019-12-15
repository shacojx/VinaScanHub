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

    public void save(List<VulnEntity> vuln,History history) {
        if (null != vuln && vuln.size() > 0) {
            try {
                Session session = factory.openSession();
                Transaction tx = session.beginTransaction();
 
                vuln.stream().map((vul) -> {
                    vul.setHistoryId(history.getId());
                    return vul;
                }).map((vul) -> {
                    session.save(vul);
                    return vul;
                }).forEachOrdered((_item) -> {
                    tx.commit();
                });
                session.clear();
                session.close();
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

    public VulnEntity getVulnEntityByID(VulnEntity vuln) {
        if (null != vuln) {
            try {
                Session session = factory.openSession();
                Transaction tx = session.beginTransaction();
                String sql = "from VulnEntity";
                VulnEntity result = (VulnEntity) session.createQuery(sql).uniqueResult();
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
