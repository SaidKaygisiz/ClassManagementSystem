package Dao;

import Entity.tanimlar;
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

public class tanimlarDAO {

    DBConnection db = new DBConnection();
    Connection con = db.connect();
    private ogretmenDAO ogretmendao;
    private gorevDAO gorevdao;
    
    public int tanimlarSayisi() {
        int ts = 0;
        try {
            PreparedStatement pst = con.prepareStatement("select * from tanimlar");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ts++;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return ts;
    }

    public List<tanimlar> getList(int page,int lic) {
        List<tanimlar> tanimlarList = new ArrayList();
        int start=0;
        start=(page-1)*lic;
        try {
            PreparedStatement pst = con.prepareStatement("select * from tanimlar order by tanimlar_id limit "+lic+" OFFSET "+start);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tanimlar tanimlar = new tanimlar();
                tanimlar.setTanimlar_id(rs.getInt("tanimlar_id"));
                tanimlar.getOgretmen().setOgrt_id(rs.getInt("ogrt_id"));
                tanimlar.getGorev().setGorev_id(rs.getInt("gorev_id"));
                tanimlar.setEkleme_tarih(rs.getString("ekleme_tarih"));
                tanimlar.setOgretmen(this.getOgretmendao().findOgretmen(rs.getInt("ogrt_id")));
                tanimlar.setGorev(this.getGorevdao().findGorev(rs.getInt("gorev_id")));
                tanimlarList.add(tanimlar);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return tanimlarList;
    }

    public void Save(tanimlar tanimlar) {
        try {
            PreparedStatement pst = con.prepareStatement("insert into tanimlar (ogrt_id,gorev_id,ekleme_tarih) values(?,?,?)");
            pst.setInt(1, tanimlar.getOgretmen().getOgrt_id());
            pst.setInt(2, tanimlar.getGorev().getGorev_id());
            pst.setString(3, sistemTarihiniGetir());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void Delete(tanimlar tanimlar) {

        try {
            PreparedStatement pst = con.prepareStatement("delete from tanimlar where tanimlar_id=" + tanimlar.getTanimlar_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(tanimlar tanimlar) {
        try {
            Statement st = con.createStatement();
            st.executeUpdate("update tanimlar set ogrt_id='" + tanimlar.getOgretmen().getOgrt_id() + "'where tanimlar_id=" + tanimlar.getTanimlar_id());
            st.executeUpdate("update tanimlar set gorev_id='" + tanimlar.getGorev().getGorev_id() + "'where tanimlar_id=" + tanimlar.getTanimlar_id());
            st.executeUpdate("update tanimlar set ekleme_tarih='" + sistemTarihiniGetir() + "' where tanimlar_id=" + tanimlar.getTanimlar_id());

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public ogretmenDAO getOgretmendao() {
        if (this.ogretmendao == null) {
            this.ogretmendao = new ogretmenDAO();
        }
        return ogretmendao;
    }

    public gorevDAO getGorevdao() {
        if (this.gorevdao == null) {
            this.gorevdao = new gorevDAO();
        }
        return gorevdao;
    }

    public String sistemTarihiniGetir() {
        Calendar takvim = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd H:mm:ss");
        return sdf.format(takvim.getTime());
    }

}
