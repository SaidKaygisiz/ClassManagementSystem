package Dao;

import Entity.birimler;
import Util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class birimlerDAO {

    DBConnection db = new DBConnection();
    Connection con = db.connect();
    private ogretmenDAO ogretmendao;

     public int birimSayisi() {
        int bs = 0;
        try {
            PreparedStatement pst = con.prepareStatement("select * from birimler");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                bs++;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return bs;
    }
    
    public List<birimler> getList(int page,int lic) {
        List<birimler> birimlerListesi = new ArrayList();
        int start=0;
        start=(page-1)*lic;
        
        try {
            PreparedStatement pst = con.prepareStatement("select * from birimler order by birim_id limit "+lic+" OFFSET "+start);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                birimler birimler = new birimler();
                birimler.setBirim_id(rs.getInt("birim_id"));
                birimler.setBolum(rs.getString("bolum"));
                birimler.setFakulte(rs.getString("fakulte"));
                birimler.getOgretmen().setOgrt_id(rs.getInt("ogrt_id"));
                birimler.setOgretmen(this.getOgretmendao().findOgretmen(rs.getInt("ogrt_id")));
                birimlerListesi.add(birimler);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return birimlerListesi;
    }

    public void Save(birimler birimler) {
        try {
            PreparedStatement pst = con.prepareStatement("insert into birimler (fakulte,bolum,ogrt_id) values(?,?,?)");
            pst.setString(1, birimler.getFakulte());
            pst.setString(2, birimler.getBolum());
            pst.setInt(3, birimler.getOgretmen().getOgrt_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

    }

    public void Delete(birimler birimler) {
        try {
            PreparedStatement pst = con.prepareStatement("delete from birimler where birim_id=" + birimler.getBirim_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void update(birimler birimler) {
        try {
            Statement st = con.createStatement();
            st.executeUpdate("update birimler set ogrt_id='" + birimler.getOgretmen().getOgrt_id() + "' where birim_id=" + birimler.getBirim_id());
            st.executeUpdate("update birimler set fakulte='" + birimler.getFakulte() + "' where birim_id=" + birimler.getBirim_id());
            st.executeUpdate("update birimler set bolum='" + birimler.getBolum() + "' where birim_id=" + birimler.getBirim_id());
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

}
