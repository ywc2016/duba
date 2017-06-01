package db;

import po.Link;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LinkDao extends BaseDao<Link> {
    private String table = "link";

    public LinkDao() {

    }

    public int add(Link link) {
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO " + table + " (url,title) VALUES (?,?);";
            ps = MysqlCon.getConn().prepareStatement(sql);
            ps.setString(1, link.getUrl());
            ps.setString(2, link.getTitle());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public long findIdByField(Class clazz, String fieldName, String value) {
        if (clazz.equals(String.class)) {
            PreparedStatement ps = null;
            try {
                String sql = "SELECT id FROM " + table + " where " + fieldName + "='" + value + "';";
                ps = MysqlCon.getConn().prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return rs.getLong("id");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0L;
    }

}