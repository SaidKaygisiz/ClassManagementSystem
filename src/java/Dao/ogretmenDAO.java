package Dao;

import Entity.ogretmen;
import Util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ogretmenDAO {

    DBConnection db = new DBConnection();
    Connection con = db.connect();
    private dokumanDAO dokumandao;
    private kullaniciDAO kuldao;

    public int ogretmenSayisi() {
        int os = 0;
        try {
            PreparedStatement pst = con.prepareStatement("select * from ogretmen");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                os++;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return os;
    }

    public List<ogretmen> getList(int page, int lic) {
        List<ogretmen> ogretmenList = new ArrayList();
        int start = 0;
        start = (page - 1) * lic;
        try {
            PreparedStatement pst = con.prepareStatement("select *from ogretmen order by ogrt_id limit " + lic + " OFFSET " + start);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ogretmen ogretmen = new ogretmen();
                ogretmen.getKul().setK_id(rs.getInt("k_id"));
                ogretmen.setOgrt_id(rs.getInt("ogrt_id"));
                ogretmen.setOgrt_giris(rs.getString("ogrt_giris"));
                ogretmen.setKul(this.getKuldao().findOgretmen(rs.getInt("k_id")));

                ogretmen.setOgretmenDokumans(this.getDokumandao().getOgretmenDokumans(ogretmen.getOgrt_id()));

                ogretmenList.add(ogretmen);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return ogretmenList;

    }

    public List<ogretmen> getListAll() {
        List<ogretmen> ogretmenList = new ArrayList();

        try {
            PreparedStatement pst = con.prepareStatement("select *from ogretmen order by ogrt_id ");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ogretmen ogretmen = new ogretmen();
                ogretmen.getKul().setK_id(rs.getInt("k_id"));
                ogretmen.setOgrt_id(rs.getInt("ogrt_id"));
                ogretmen.setOgrt_giris(rs.getString("ogrt_giris"));
                ogretmen.setKul(this.getKuldao().findOgretmen(rs.getInt("k_id")));

                ogretmen.setOgretmenDokumans(this.getDokumandao().getOgretmenDokumans(ogretmen.getOgrt_id()));

                ogretmenList.add(ogretmen);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return ogretmenList;

    }
    
    public List<ogretmen> getListLastFive() {
        List<ogretmen> ogretmenList = new ArrayList();

        try {
            PreparedStatement pst = con.prepareStatement("select *from ogretmen order by k_id desc limit "+5);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ogretmen ogretmen = new ogretmen();
                ogretmen.getKul().setK_id(rs.getInt("k_id"));
                ogretmen.setOgrt_id(rs.getInt("ogrt_id"));
                ogretmen.setOgrt_giris(rs.getString("ogrt_giris"));
                ogretmen.setKul(this.getKuldao().findOgretmen(rs.getInt("k_id")));
                ogretmen.setOgretmenDokumans(this.getDokumandao().getOgretmenDokumans(ogretmen.getOgrt_id()));
                ogretmenList.add(ogretmen);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return ogretmenList;

    }

    public void Delete(ogretmen ogretmen) {

        try {
            PreparedStatement pst = con.prepareStatement("delete from ogretmen where ogrt_id=" + ogretmen.getOgrt_id(), PreparedStatement.RETURN_GENERATED_KEYS);
            pst.executeUpdate();

            PreparedStatement ps = con.prepareStatement("delete from kullanici where k_id=" + ogretmen.getKul().getK_id());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void Save(ogretmen ogretmen) {
        try {
            PreparedStatement ps = con.prepareStatement("insert into kullanici (ad,soyad,email,tel_no,sifre,grup_id) values(?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, ogretmen.getKul().getAd());
            ps.setString(2, ogretmen.getKul().getSoyad());
            ps.setString(3, ogretmen.getKul().getEmail());
            ps.setString(4, ogretmen.getKul().getTel_no());
            ps.setString(5, ogretmen.getKul().getSifre());
            ps.setInt(6, 2);
            ps.executeUpdate();

            int k_id = 0;
            ResultSet gk = ps.getGeneratedKeys();
            if (gk.next()) {
                k_id = gk.getInt(1);
            }
            PreparedStatement pst = con.prepareStatement("insert into ogretmen (k_id,ogrt_giris) values(?,?)");
            pst.setInt(1, k_id);
            pst.setString(2, sistemTarihiniGetir());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

    }

    public void update(ogretmen ogretmen) {
        try {
            Statement st = con.createStatement();
            st.executeUpdate("update kullanici set ad='" + ogretmen.getKul().getAd() + "' where k_id=" + ogretmen.getKul().getK_id());
            st.executeUpdate("update kullanici set soyad='" + ogretmen.getKul().getSoyad() + "' where k_id=" + ogretmen.getKul().getK_id());
            st.executeUpdate("update kullanici set email='" + ogretmen.getKul().getEmail() + "' where k_id=" + ogretmen.getKul().getK_id());
            st.executeUpdate("update kullanici set tel_no='" + ogretmen.getKul().getTel_no() + "' where k_id=" + ogretmen.getKul().getK_id());

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public dokumanDAO getDokumandao() {
        if (this.dokumandao == null) {
            this.dokumandao = new dokumanDAO();
        }
        return dokumandao;
    }

    public kullaniciDAO getKuldao() {
        if (this.kuldao == null) {
            this.kuldao = new kullaniciDAO();
        }
        return kuldao;
    }

    public ogretmen findOgretmen(int id) {
        ogretmen ogrt = null;
        try {
            PreparedStatement pst = con.prepareStatement("select * from ogretmen where ogrt_id=" + id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            ogrt = new ogretmen();
            ogrt.setOgrt_id(rs.getInt("ogrt_id"));
            ogrt.setOgrt_giris(rs.getString("ogrt_giris"));
            ogrt.getKul().setK_id(rs.getInt("k_id"));
            ogrt.setKul(this.getKuldao().findOgretmen(rs.getInt("k_id")));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ogrt;
    }

    public ogretmen bulOgretmen(int id) {
        ogretmen ogrt = null;
        try {
            PreparedStatement pst = con.prepareStatement("select * from ogretmen where ogrt_id=" + id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            ogrt = new ogretmen();
            ogrt.setOgrt_id(rs.getInt("ogrt_id"));
            ogrt.setOgrt_giris(rs.getString("ogrt_giris"));
            ogrt.getKul().setK_id(rs.getInt("k_id"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return ogrt;
    }
    public String sistemTarihiniGetir() {
        Calendar takvim = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd H:mm:ss");
        System.out.println(sdf.format(takvim.getTime()));
        return sdf.format(takvim.getTime());
    }

}
