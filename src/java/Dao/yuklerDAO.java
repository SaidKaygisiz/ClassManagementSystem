package Dao;

import Entity.yukler;
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

public class yuklerDAO {

    DBConnection db = new DBConnection();
    Connection con = db.connect();
    private derslerDAO derslerdao;
    private dokumanDAO dokumandao;
    private ogretmenDAO ogrtdao;
    
     public int yuklerSayisi() {
        int ys = 0;
        try {
            PreparedStatement pst = con.prepareStatement("select * from yukler");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ys++;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return ys;
    }

    public List<yukler> getList(int page,int lic) {
        List<yukler> yuklerList = new ArrayList();
        int start=0;
        start=(page-1)*lic;
        try {
            PreparedStatement pst = con.prepareStatement("select *from yukler order by ekleme_tarih limit "+lic+" OFFSET "+start);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                yukler yukler = new yukler();
                yukler.setYukler_id(rs.getInt("yukler_id"));
                yukler.setOgrt_id(rs.getInt("ogrt_id"));
                yukler.setDers_id(rs.getInt("ders_id"));
                yukler.setDok_id(rs.getInt("dok_id"));
                yukler.setEkleme_tarih(rs.getString("ekleme_tarih"));
                yukler.setDokuman(this.getDokumandao().find(rs.getInt("dok_id")));
                yukler.setDers(this.getDerslerdao().find(rs.getInt("ders_id")));
                yukler.setOgrt(this.getOgrtdao().findOgretmen(rs.getInt("ogrt_id")));
                yuklerList.add(yukler);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return yuklerList;
    }

    public void Delete(yukler yukler) {
        try {
            PreparedStatement pst = con.prepareStatement("delete from yukler where yukler_id=" + yukler.getYukler_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public void Save(yukler yukler) {
            System.out.println(yukler.getOgrt_id());
            System.out.println(yukler.getDers_id());
            System.out.println(yukler.getDok_id());
        try {
            PreparedStatement pst = con.prepareStatement("insert into yukler (ogrt_id,dok_id,ders_id,ekleme_tarih) values(?,?,?,?)");
            pst.setInt(1, yukler.getOgrt().getOgrt_id());
            pst.setInt(2, yukler.getDokuman().getDok_id());
            pst.setInt(3, yukler.getDers().getDers_id());
            pst.setString(4, sistemTarihiniGetir());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

    }

    public void update(yukler yukler) {
        try {
            Statement st = con.createStatement();
            st.executeUpdate("update yukler set ogrt_id='" + yukler.getOgrt().getOgrt_id() + "' where yukler_id=" + yukler.getYukler_id());
            st.executeUpdate("update yukler set dok_id='" + yukler.getDokuman().getDok_id() + "' where yukler_id=" + yukler.getYukler_id());
            st.executeUpdate("update yukler set ders_id='" + yukler.getDers().getDers_id() + "' where yukler_id=" + yukler.getYukler_id());
            st.executeUpdate("update yukler set ekleme_tarih='" + sistemTarihiniGetir() + "' where yukler_id=" + yukler.getYukler_id());

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public derslerDAO getDerslerdao() {
        if (this.derslerdao == null) {
            this.derslerdao = new derslerDAO();
        }
        return derslerdao;
    }

    public ogretmenDAO getOgrtdao() {
        if (this.ogrtdao == null) {
            this.ogrtdao = new ogretmenDAO();
        }
        return ogrtdao;
    }

    public dokumanDAO getDokumandao() {
        if (this.dokumandao == null) {
            this.dokumandao = new dokumanDAO();
        }
        return dokumandao;
    }

    public String sistemTarihiniGetir() {
        Calendar takvim = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd H:mm:ss");
        System.out.println(sdf.format(takvim.getTime()));
        return sdf.format(takvim.getTime());
    }

}
