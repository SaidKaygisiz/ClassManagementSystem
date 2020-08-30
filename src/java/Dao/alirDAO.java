
package Dao;

import Entity.alir;
import Util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class alirDAO {

    DBConnection db = new DBConnection();
    Connection con = db.connect();
    private derslerDAO derslerdao;
    private ogrenciDAO ogrencidao;

    
    public int alirSayisi() {
        int as = 0;
        try {
            PreparedStatement pst = con.prepareStatement("select * from alir");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                as++;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return as;
    }
    
    public List<alir> getList(int page,int lic) {
        List<alir> alirList = new ArrayList();
        int start=0;
        start=(page-1)*lic;
        try {
            PreparedStatement pst = con.prepareStatement("select * from alir order by ogr_id limit "+lic+" OFFSET "+start);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                alir alir = new alir();
                alir.setAlir_id(rs.getInt("alir_id"));
                alir.getOgrenci().setOgr_id(rs.getInt("ogr_id"));
                alir.setDers_id(rs.getInt("ders_id"));
                alir.setAlir_puani(rs.getLong("alir_puani"));
                alir.setDersler(this.getDerslerdao().find(rs.getInt("ders_id")));
                alir.setOgrenci(this.getOgrencidao().findOgrenci(rs.getInt("ogr_id")));
                alirList.add(alir);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return alirList;
    }

    public void Save(alir alir)throws ClassCastException {

        try {
            PreparedStatement pst = con.prepareStatement("insert into alir (ogr_id,ders_id,alir_puani) values(?,?,?)");
            pst.setInt(1, alir.getOgrenci().getOgr_id());
            pst.setInt(2, alir.getDersler().getDers_id());
            pst.setLong(3, alir.getAlir_puani());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

    }

    public void Delete(alir alir) {

        try {
            PreparedStatement pst = con.prepareStatement("delete from alir where alir_id=" + alir.getAlir_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void update(alir alir) {

        try {
            Statement st = con.createStatement();
            st.executeUpdate("update alir set alir_puani='" + alir.getAlir_puani() + "' where alir_id=" + alir.getAlir_id());
            st.executeUpdate("update alir set ogr_id='" + alir.getOgrenci().getOgr_id() + "' where alir_id=" + alir.getAlir_id());
            st.executeUpdate("update alir set ders_id='" + alir.getDersler().getDers_id() + "' where alir_id=" + alir.getAlir_id());

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public Long puanGetir(int a, int b) {
        Long puan = null;
         try {
            PreparedStatement pst = con.prepareStatement("Select alir_puani from alir where ogr_id=? and ders_id=?");
            pst.setInt(1, a);
            pst.setInt(2, b);
            ResultSet rs=pst.executeQuery();
            rs.next();
            puan=rs.getLong("alir_puani");
             
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         
        return puan;
    }

    public derslerDAO getDerslerdao() {
        if (this.derslerdao == null) {
            this.derslerdao = new derslerDAO();
        }
        return derslerdao;
    }

    public ogrenciDAO getOgrencidao() {
        if (this.ogrencidao == null) {
            this.ogrencidao = new ogrenciDAO();
        }
        return ogrencidao;
    }

}
