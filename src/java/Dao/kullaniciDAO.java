package Dao;

import Bean.kullaniciController;
import Entity.kullanici;
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

public class kullaniciDAO {

    DBConnection db = new DBConnection();
    Connection con = db.connect();
    private grupDAO grupdao;
    kullaniciController kul;

    public int kullaniciSayisi() {
        int ks = 0;
        try {
            PreparedStatement pst = con.prepareStatement("select * from kullanici");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ks++;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return ks;
    }

    public List<kullanici> getList(int page, int lic) {
        List<kullanici> kullaniciList = new ArrayList();
        int start = 0;
        start = (page - 1) * lic;
        try {
            PreparedStatement pst = con.prepareStatement("select * from kullanici order by k_id limit " + lic + " OFFSET " + start);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                kullanici kullanici = new kullanici();
                kullanici.setK_id(rs.getInt("k_id"));
                kullanici.setAd(rs.getString("ad"));
                kullanici.setSoyad(rs.getString("soyad"));
                kullanici.setEmail(rs.getString("email"));
                kullanici.setSifre(rs.getString("sifre"));
                kullanici.setTel_no(rs.getString("tel_no"));
                kullanici.getGrup().setGrup_id(rs.getInt("grup_id"));
                kullanici.setGrup(this.getGrupdao().find(rs.getInt("grup_id")));
                kullaniciList.add(kullanici);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return kullaniciList;
    }

    public void Save(kullanici kullanici) {
        try {
            PreparedStatement pst = con.prepareStatement("insert into kullanici(ad,soyad,email,sifre,tel_no,grup_id) values(?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);

            pst.setString(1, kullanici.getAd().toUpperCase());
            pst.setString(2, kullanici.getSoyad().toUpperCase());
            pst.setString(3, kullanici.getEmail());
            pst.setString(4, kullanici.getSifre());
            pst.setString(5, kullanici.getTel_no());
            pst.setInt(6, kullanici.getGrup().getGrup_id());
            pst.executeUpdate();
            int k_id = 0;
            ResultSet gk = pst.getGeneratedKeys();
            if (gk.next()) {
                k_id = gk.getInt(1);
            }

            switch (kullanici.getGrup().getGrup_id()) {
                case 3: {
                    PreparedStatement ps = con.prepareStatement("insert into ogrenci (k_id,sinif) values(?,?)");
                    ps.setInt(1, k_id);
                    ps.setLong(2, kullanici.getOgrenci().getSinif());
                    ps.executeUpdate();
                    break;
                }
                case 2: {
                    PreparedStatement ps = con.prepareStatement("insert into ogretmen (k_id,ogrt_giris) values(?,?)");
                    ps.setInt(1, k_id);
                    ps.setString(2, sistemTarihiniGetir());
                    ps.executeUpdate();
                    break;
                }
                case 1: {
                    PreparedStatement ps = con.prepareStatement("insert into kullanici (k_id) values(?)");
                    ps.setInt(1, k_id);
                    ps.executeUpdate();
                    break;
                }
                default:
                    break;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void Save2(kullanici kullanici) {
        try {
            PreparedStatement pst = con.prepareStatement("insert into kullanici(ad,soyad,email,sifre,tel_no,grup_id) values(?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);

            pst.setString(1, kullanici.getAd().toUpperCase());
            pst.setString(2, kullanici.getSoyad().toUpperCase());
            pst.setString(3, kullanici.getEmail());
            pst.setString(4, kullanici.getSifre());
            pst.setString(5, kullanici.getTel_no());
            pst.setInt(6, kullanici.getGrup_id());
            pst.executeUpdate();
            int k_id = 0;
            ResultSet gk = pst.getGeneratedKeys();
            if (gk.next()) {
                k_id = gk.getInt(1);
            }

            switch (kullanici.getGrup_id()) {
                case 3: {
                    PreparedStatement ps = con.prepareStatement("insert into ogrenci (k_id,sinif) values(?,?)");
                    ps.setInt(1, k_id);
                    ps.setLong(2, kullanici.getOgrenci().getSinif());
                    ps.executeUpdate();

                    break;
                }
                case 2: {
                    PreparedStatement ps = con.prepareStatement("insert into ogretmen (k_id,ogrt_giris) values(?,?)");
                    ps.setInt(1, k_id);
                    ps.setString(2, sistemTarihiniGetir());
                    ps.executeUpdate();

                    break;
                }
                default:
                    break;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void Delete(kullanici kullanici) {
        try {
            PreparedStatement pst = con.prepareStatement("delete from kullanici where k_id=" + kullanici.getK_id(), PreparedStatement.RETURN_GENERATED_KEYS);
            pst.executeUpdate();
            int k_id = 0;
            ResultSet gk = pst.getGeneratedKeys();
            if (gk.next()) {
                k_id = gk.getInt(1);
            }
            if (kullanici.getGrup().getGrup_id() == 3) {
                PreparedStatement ps = con.prepareStatement("delete from ogrenci where k_id=" + k_id);
                ps.executeUpdate();
            } else if (kullanici.getGrup().getGrup_id() == 2) {
                PreparedStatement ps = con.prepareStatement("delete from ogretmen where k_id=" + k_id);
                ps.executeUpdate();

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void update(kullanici kullanici) {

        try {
            Statement st = con.createStatement();

            st.executeUpdate("update kullanici set ad='" + kullanici.getAd().toUpperCase() + "' where k_id=" + kullanici.getK_id());
            st.executeUpdate("update kullanici set soyad='" + kullanici.getSoyad().toUpperCase() + "' where k_id=" + kullanici.getK_id());
            st.executeUpdate("update kullanici set email='" + kullanici.getEmail() + "' where k_id=" + kullanici.getK_id());
            st.executeUpdate("update kullanici set tel_no='" + kullanici.getTel_no() + "' where k_id=" + kullanici.getK_id());
            st.executeUpdate("update kullanici set grup_id='" + kullanici.getGrup().getGrup_id() + "' where k_id=" + kullanici.getK_id());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public DBConnection getDb() {
        if (this.db == null) {
            this.db = new DBConnection();
        }
        return db;
    }

    public Connection getCon() {
        if (this.con == null) {
            this.con = db.connect();
        }

        return con;
    }

    public grupDAO getGrupdao() {
        if (this.grupdao == null) {
            this.grupdao = new grupDAO();
        }
        return grupdao;
    }

    public kullanici findOgrenci(int id) {
        kullanici k = null;
        try {
            PreparedStatement pst = con.prepareStatement("select * from kullanici where k_id=" + id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            k = new kullanici();
            k.setK_id(rs.getInt("k_id"));
            k.setAd(rs.getString("ad"));
            k.setSoyad(rs.getString("soyad"));
            k.setEmail(rs.getString("email"));
            k.setSifre(rs.getString("sifre"));
            k.setTel_no(rs.getString("tel_no"));
            k.getGrup().setGrup_id(rs.getInt("grup_id"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return k;
    }

    public kullanici findOgretmen(int id) {
        kullanici k = null;

        try {
            PreparedStatement pst = con.prepareStatement("select * from kullanici where k_id=" + id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            k = new kullanici();
            k.setK_id(rs.getInt("k_id"));
            k.setAd(rs.getString("ad"));
            k.setSoyad(rs.getString("soyad"));
            k.setEmail(rs.getString("email"));
            k.setSifre(rs.getString("sifre"));
            k.setTel_no(rs.getString("tel_no"));
            k.getGrup().setGrup_id(rs.getInt("grup_id"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return k;
    }

    public int varMi(String email, String Sifre) {
        int grup = 0;
        try {
            PreparedStatement pst = con.prepareStatement("select email,sifre from kullanici where email=? and sifre=?");
            pst.setString(1, email);
            pst.setString(2, Sifre);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                PreparedStatement ps = con.prepareStatement("select grup_id from kullanici where email=?");
                ps.setString(1, email);
                ResultSet r = ps.executeQuery();
                r.next();
                grup = r.getInt("grup_id");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return grup;
    }

    public kullanici filter(kullanici kul) {
        kullanici k = null;

        try {
            PreparedStatement pst = con.prepareStatement("select * from kullanici where email=" + kul.getEmail());
            ResultSet rs = pst.executeQuery();
            rs.next();
            k = new kullanici();
            k.setK_id(rs.getInt("k_id"));
            k.setAd(rs.getString("ad"));
            k.setSoyad(rs.getString("soyad"));
            k.setEmail(rs.getString("email"));
            k.setSifre(rs.getString("sifre"));
            k.setTel_no(rs.getString("tel_no"));
            k.getGrup().setGrup_id(rs.getInt("grup_id"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return k;
    }

    public String sistemTarihiniGetir() {
        Calendar takvim = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd H:mm:ss");
        System.out.println(sdf.format(takvim.getTime()));
        return sdf.format(takvim.getTime());
    }

}
