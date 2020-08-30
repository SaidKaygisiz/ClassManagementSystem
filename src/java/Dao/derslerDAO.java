package Dao;

import Entity.dersler;
import Util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class derslerDAO {

    DBConnection db = new DBConnection();
    Connection con = db.connect();
    private ogrenciDAO odao;

    public int dersSayisi() {
        int ds = 0;
        try {
            PreparedStatement pst = con.prepareStatement("select * from dersler");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ds++;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return ds;
    }

    public dersler find(int id) {
        dersler d = null;

        try {
            PreparedStatement pst = con.prepareStatement("select * from dersler where ders_id=" + id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            d = new dersler();
            d.setDers_id(rs.getInt("ders_id"));
            d.setDers_ad(rs.getString("ders_ad"));
            d.setAkts(rs.getLong("akts"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return d;
    }

    public List<dersler> getList(int page, int lic) {
        List<dersler> dersListesi = new ArrayList();
        int start = 0;
        start = (page - 1) * lic;

        try {
            PreparedStatement pst = con.prepareStatement("select *from dersler order by ders_id limit " + lic + " OFFSET " + start);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                dersler dersler = new dersler();
                dersler.setDers_id(rs.getInt("ders_id"));
                dersler.setDers_ad(rs.getString("ders_ad"));
                dersler.setAkts(rs.getLong("akts"));
                dersler.setOgrenciList(this.getOdao().getDersOgrenciler(dersler.getDers_id()));
                dersListesi.add(dersler);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return dersListesi;
    }

    public List<dersler> getListAll() {
        List<dersler> dersListesi = new ArrayList();

        try {
            PreparedStatement pst = con.prepareStatement("select *from dersler order by ders_id ");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                dersler dersler = new dersler();
                dersler.setDers_id(rs.getInt("ders_id"));
                dersler.setDers_ad(rs.getString("ders_ad"));
                dersler.setAkts(rs.getLong("akts"));
                dersler.setOgrenciList(this.getOdao().getDersOgrenciler(dersler.getDers_id()));
                dersListesi.add(dersler);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return dersListesi;
    }

    public void delete(dersler ders) {
        try {
            PreparedStatement pst = con.prepareStatement("delete from dersler where ders_id=" + ders.getDers_id());
            pst.executeUpdate();
            

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    public void Save(dersler dersler) {

        try {
            PreparedStatement pst = con.prepareStatement("insert into dersler (ders_ad,akts) values(?,?)");
            pst.setString(1, dersler.getDers_ad());
            pst.setLong(2, dersler.getAkts());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

    }

    public void update(dersler dersler) {
        try {
            Statement st = con.createStatement();
            st.executeUpdate("update dersler set ders_ad='" + dersler.getDers_ad() + "' where ders_id=" + dersler.getDers_id());
            st.executeUpdate("update dersler set akts='" + dersler.getAkts() + "' where ders_id=" + dersler.getDers_id());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public List<dersler> getOgrenciDersler(int ogr_id) {
        List<dersler> ogrenciDersler = new ArrayList<>();

        try {
            PreparedStatement pst = con.prepareStatement("select *from alir where ogr_id=" + ogr_id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ogrenciDersler.add(this.find(rs.getInt("ders_id")));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return ogrenciDersler;
    }

    public ogrenciDAO getOdao() {
        if (this.odao == null) {
            this.odao = new ogrenciDAO();
        }
        return odao;
    }

}
