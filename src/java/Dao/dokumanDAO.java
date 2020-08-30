package Dao;

import Entity.dokuman;
import Util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class dokumanDAO {

    DBConnection db = new DBConnection();
    Connection con = db.connect();
    
     public int dokumanSayisi() {
        int ds = 0;
        try {
            PreparedStatement pst = con.prepareStatement("select * from dokuman");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ds++;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return ds;
    }

    public List<dokuman> getList(int page,int lic) {
        List<dokuman> dokumanListesi = new ArrayList();
        int start=0;
        start=(page-1)*lic;
        try {
            PreparedStatement pst = con.prepareStatement("select *from dokuman order by dok_id limit "+lic+" OFFSET "+start);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                dokuman dokuman = new dokuman();
                dokuman.setDok_id(rs.getInt("dok_id"));
                dokuman.setDok_ad(rs.getString("dok_ad"));
                dokuman.setUzanti(rs.getString("uzanti"));
                dokuman.setTip(rs.getString("tip"));
                dokumanListesi.add(dokuman);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return dokumanListesi;

    }
    
    public List<dokuman> getListAll() {
        List<dokuman> dokumanListesi = new ArrayList();
       
        try {
            PreparedStatement pst = con.prepareStatement("select *from dokuman order by dok_id");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                dokuman dokuman = new dokuman();
                dokuman.setDok_id(rs.getInt("dok_id"));
                dokuman.setDok_ad(rs.getString("dok_ad"));
                dokuman.setUzanti(rs.getString("uzanti"));
                dokuman.setTip(rs.getString("tip"));
                dokumanListesi.add(dokuman);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return dokumanListesi;

    }
     public List<dokuman> getListLastFive() {
        List<dokuman> dokumanListesi = new ArrayList();
       
        try {
            PreparedStatement pst = con.prepareStatement("select *from dokuman order by dok_id desc limit "+5);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                dokuman dokuman = new dokuman();
                dokuman.setDok_id(rs.getInt("dok_id"));
                dokuman.setDok_ad(rs.getString("dok_ad"));
                dokuman.setUzanti(rs.getString("uzanti"));
                dokuman.setTip(rs.getString("tip"));
                dokumanListesi.add(dokuman);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return dokumanListesi;

    }

    public void Delete(dokuman dokuman) {
        try {
            PreparedStatement pst = con.prepareStatement("delete from dokuman where dok_id=" + dokuman.getDok_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void Save(dokuman dokuman) {

        try {
            PreparedStatement pst = con.prepareStatement("insert into dokuman (dok_ad,uzanti,tip) values(?,?,?)");
            pst.setString(1, dokuman.getDok_ad());
            pst.setString(2, dokuman.getUzanti());
            pst.setString(3, dokuman.getTip());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

    }

    public void update(dokuman dokuman) {
        try {
            Statement st = con.createStatement();
            st.executeUpdate("update dokuman set dok_ad='" + dokuman.getDok_ad() + "' where dok_id=" + dokuman.getDok_id());
            st.executeUpdate("update dokuman set uzanti='" + dokuman.getUzanti() + "' where dok_id=" + dokuman.getDok_id());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public List<dokuman> getOgretmenDokumans(int ogrt_id) {
        List<dokuman> ogretmenDokumans = new ArrayList<>();

        try {
            PreparedStatement pst = con.prepareStatement("select *from yukler where ogrt_id=" + ogrt_id);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                ogretmenDokumans.add(this.find(rs.getInt("dok_id")));

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return ogretmenDokumans;
    }

    public dokuman find(int id) {
        dokuman d = null;

        try {

            PreparedStatement pst = con.prepareStatement("select *from dokuman where dok_id=" + id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            d = new dokuman();
            d.setDok_id(rs.getInt("dok_id"));
            d.setDok_ad(rs.getString("dok_ad"));
            d.setUzanti(rs.getString("uzanti"));
            d.setTip(rs.getString("tip"));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return d;
    }

}
