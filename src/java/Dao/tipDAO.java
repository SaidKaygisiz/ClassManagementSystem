package Dao;

import Entity.tip;
import Util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class tipDAO {

    DBConnection db = new DBConnection();
    Connection con = db.connect();

    public List<tip> getList() {
        List<tip> tipListesi = new ArrayList();

        try {
            PreparedStatement pst = con.prepareStatement("select *from tip");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                tip tip = new tip();
                tip.setTip_id(rs.getInt("tip_id"));
                tip.setTip_ad(rs.getString("tip_ad"));
                tipListesi.add(tip);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return tipListesi;
    }

    public void Save(tip tip) {
        try {
            PreparedStatement pst = con.prepareStatement("insert into tip (tip_ad) values(?)");
            pst.setString(1, tip.getTip_ad());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }

    public void Delete(tip tip) {
        try {
            PreparedStatement pst = con.prepareStatement("delete from tip where tip_id=" + tip.getTip_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(tip tip) {
        try {
            Statement st = con.createStatement();
            st.executeUpdate("update tip set tip_ad='" + tip.getTip_ad() + "' where tip_id=" + tip.getTip_id());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public tip find(int id) {
        tip t = null;

        try {
            PreparedStatement pst = con.prepareStatement("select * from tip where tip_id=" + id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            t = new tip();
            t.setTip_id(rs.getInt("tip_id"));
            t.setTip_ad(rs.getString("tip_ad"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return t;
    }

}
