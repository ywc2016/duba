package db;

import org.hibernate.Session;
import po.Link;

/**
 * Created by yuwc on 2017/6/1.
 */
public class BaseDao<T> {

    public void save(T pojo) {
        Session session = null;
        try {
            session = Client.getSessionFactory().openSession();
            //开启事务
            session.beginTransaction();

            session.save(pojo);
            //提交事务
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            //回滚事务
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                if (session.isOpen()) {
                    //关闭session
                    session.close();
                }
            }
        }
    }
}
