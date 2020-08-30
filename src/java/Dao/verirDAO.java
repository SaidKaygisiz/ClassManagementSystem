package Dao;

import Entity.verir;
import Util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class verirDAO {

    DBConnection db = new DBConnection();
    Connection con = db.connect();
    private derslerDAO derslerdao;
    private ogretmenDAO ogretmendao;
    
     public int verirSayisi() {
        int vs = 0;
        try {
            PreparedStatement pst = con.prepareStatement("select * from verir");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                vs++;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return vs;
    }

    public List<verir> getList(int page,int lic) {
        List<verir> verirList = new ArrayList();
        int start=0;
        start=(page-1)*lic;
        try {
            PreparedStatement pst = con.prepareStatement("select * from verir order by verir_id limit "+lic+" OFFSET "+start);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                verir verir = new verir();
                verir.setVerir_id(rs.getInt("verir_id"));
                verir.getOgretmen().setOgrt_id(rs.getInt("ogrt_id"));
                verir.getDersler().setDers_id(rs.getInt("ders_id"));
                verir.setVerir_donem(rs.getString("verir_donem"));
                verir.setDersler(this.getDerslerdao().find(rs.getInt("ders_id")));
                verir.setOgretmen(this.getOgretmendao().findOgretmen(rs.getInt("ogrt_id")));
                verirList.add(verir);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return verirList;
    }

    public void Save(verir verir) {

        try {
            PreparedStatement pst = con.prepareStatement("insert into verir (ogrt_id,ders_id,verir_donem) values(?,?,?)");
            pst.setInt(1, verir.getOgretmen().getOgrt_id());
            pst.setInt(2, verir.getDersler().getDers_id());
            pst.setString(3, verir.getVerir_donem());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

    }

    public void update(verir verir) {
        try {
            Statement st = con.createStatement();
            st.executeUpdate("update verir set ogrt_id='" + verir.getOgretmen().getOgrt_id() + "' where verir_id=" + verir.getVerir_id());
            st.executeUpdate("update verir set ders_id='" + verir.getDersler().getDers_id() + "' where verir_id=" + verir.getVerir_id());
            st.executeUpdate("update verir set verir_donem='" + verir.getVerir_donem() + "' where verir_id=" + verir.getVerir_id());

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void Delete(verir verir) {

        try {
            PreparedStatement pst = con.prepareStatement("delete from verir where verir_id=" + verir.getVerir_id());
            pst.executeUpdate();

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

    public ogretmenDAO getOgretmendao() {
        if (this.ogretmendao == null) {
            this.ogretmendao = new ogretmenDAO();
        }
        return ogretmendao;
    }

}
