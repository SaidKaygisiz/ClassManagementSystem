package Dao;

import Entity.ogrenci;
import Util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ogrenciDAO {

    DBConnection db = new DBConnection();
    Connection con = db.connect();
    private kullaniciDAO kullanicidao;
    private derslerDAO dersdao;

    public int ogrenciSayisi() {
        int os = 0;
        try {
            PreparedStatement pst = con.prepareStatement("select * from ogrenci");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                os++;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return os;
    }

    public List<ogrenci> getList(int page, int lic) {
        List<ogrenci> ogrenciList = new ArrayList();
        int start = 0;
        start = (page - 1) * lic;
        try {
            PreparedStatement pst = con.prepareStatement("select * from ogrenci order by k_id limit " + lic + " OFFSET " + start);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                ogrenci ogrenci = new ogrenci();
                ogrenci.getKul().setK_id(rs.getInt("k_id"));
                ogrenci.setOgr_id(rs.getInt("ogr_id"));
                ogrenci.setSinif(rs.getLong("sinif"));
                ogrenci.setKul(this.getKullanicidao().findOgrenci(rs.getInt("k_id")));
                ogrenci.setOgrenciDersler(this.getDersdao().getOgrenciDersler(ogrenci.getOgr_id()));
                ogrenciList.add(ogrenci);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return ogrenciList;

    }

    public List<ogrenci> getListAll() {
        List<ogrenci> ogrenciList = new ArrayList();

        try {
            PreparedStatement pst = con.prepareStatement("select * from ogrenci order by k_id");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                ogrenci ogrenci = new ogrenci();
                ogrenci.getKul().setK_id(rs.getInt("k_id"));
                ogrenci.setOgr_id(rs.getInt("ogr_id"));
                ogrenci.setSinif(rs.getLong("sinif"));
                ogrenci.setKul(this.getKullanicidao().findOgrenci(rs.getInt("k_id")));
                ogrenci.setOgrenciDersler(this.getDersdao().getOgrenciDersler(ogrenci.getOgr_id()));
                ogrenciList.add(ogrenci);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return ogrenciList;

    }

    public List<ogrenci> getListLastFive() {
        List<ogrenci> ogrenciList = new ArrayList();

        try {
            PreparedStatement pst = con.prepareStatement("select * from ogrenci order by k_id desc limit " + 5);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                ogrenci ogrenci = new ogrenci();
                ogrenci.getKul().setK_id(rs.getInt("k_id"));
                ogrenci.setOgr_id(rs.getInt("ogr_id"));
                ogrenci.setSinif(rs.getLong("sinif"));
                ogrenci.setKul(this.getKullanicidao().findOgrenci(rs.getInt("k_id")));
                ogrenci.setOgrenciDersler(this.getDersdao().getOgrenciDersler(ogrenci.getOgr_id()));
                ogrenciList.add(ogrenci);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return ogrenciList;

    }

    public void update(ogrenci ogrenci) {
        try {
            Statement st = con.createStatement();
            st.executeUpdate("update ogrenci set sinif='" + ogrenci.getSinif() + "' where ogr_id=" + ogrenci.getOgr_id());
            st.executeUpdate("update kullanici set tel_no='" + ogrenci.getKul().getTel_no() + "' where k_id=" + ogrenci.getKul().getK_id());
            st.executeUpdate("update kullanici set ad='" + ogrenci.getKul().getAd() + "' where k_id=" + ogrenci.getKul().getK_id());
            st.executeUpdate("update kullanici set soyad='" + ogrenci.getKul().getSoyad() + "' where k_id=" + ogrenci.getKul().getK_id());
            st.executeUpdate("update kullanici set email='" + ogrenci.getKul().getEmail() + "' where k_id=" + ogrenci.getKul().getK_id());

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void Save(ogrenci ogrenci) {
        try {

            PreparedStatement ps = con.prepareStatement("insert into kullanici (ad,soyad,email,sifre,tel_no,grup_id) values(?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, ogrenci.getKul().getAd());
            ps.setString(2, ogrenci.getKul().getSoyad());
            ps.setString(3, ogrenci.getKul().getEmail());
            ps.setString(4, ogrenci.getKul().getSifre());
            ps.setString(5, ogrenci.getKul().getTel_no());
            ps.setInt(6, 3);
            ps.executeUpdate();
            int k_id = 0;
            ResultSet gk = ps.getGeneratedKeys();
            if (gk.next()) {
                k_id = gk.getInt(1);
            }

            PreparedStatement pst = con.prepareStatement("insert into ogrenci (k_id,sinif) values(?,?)");
            pst.setInt(1, k_id);
            pst.setLong(2, ogrenci.getSinif());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

    }

    public void Delete(ogrenci ogrenci) {
        try {
            PreparedStatement pst = con.prepareStatement("delete from ogrenci where ogr_id=" + ogrenci.getOgr_id(), PreparedStatement.RETURN_GENERATED_KEYS);
            pst.executeUpdate();
            int ogr_id = 0;
            ResultSet gk = pst.getGeneratedKeys();
            if (gk.next()) {
                ogr_id = gk.getInt(1);
            }

            PreparedStatement ps = con.prepareStatement("delete from alir where ogr_id=" + ogr_id);
            PreparedStatement p = con.prepareStatement("delete from kullanici where k_id=" + ogrenci.getKul().getK_id());
            ps.executeUpdate();
            p.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public ogrenci findOgrenci(int id) {

        ogrenci o = null;

        try {
            PreparedStatement pst = con.prepareStatement("select * from ogrenci where ogr_id=" + id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            o = new ogrenci();
            o.getKul().setK_id(rs.getInt("k_id"));
            o.setOgr_id(rs.getInt("ogr_id"));
            o.setSinif(rs.getLong("sinif"));
            o.setKul(this.getKullanicidao().findOgrenci(rs.getInt("k_id")));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return o;

    }

    public kullaniciDAO getKullanicidao() {
        if (this.kullanicidao == null) {
            this.kullanicidao = new kullaniciDAO();
        }
        return kullanicidao;
    }

    public derslerDAO getDersdao() {
        if (this.dersdao == null) {
            this.dersdao = new derslerDAO();
        }
        return dersdao;
    }

    public List<ogrenci> getDersOgrenciler(int ders_id) {
        List<ogrenci> dersOgrenciler = new ArrayList<>();

        try {
            PreparedStatement pst = con.prepareStatement("select * from alir where ders_id=" + ders_id);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                dersOgrenciler.add(this.findOgrenci(rs.getInt("ogr_id")));

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return dersOgrenciler;
    }

    public DBConnection getDb() {
        return db;
    }

    public Connection getCon() {
        return con;
    }

}
