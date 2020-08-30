package Dao;

import Entity.gorev;
import Util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class gorevDAO {

    DBConnection db = new DBConnection();
    Connection con = db.connect();
    private tipDAO tipdao;

    public int gorevSayisi() {
        int gs = 0;
        try {
            PreparedStatement pst = con.prepareStatement("select * from gorev");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                gs++;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return gs;
    }
    
    public List<gorev> getList(int page,int lic) {
        List<gorev> gorevListesi = new ArrayList();
        int start=0;
        start=(page-1)*lic;
        try {
            PreparedStatement pst = con.prepareStatement("select *from gorev order by gorev_id limit "+lic+" OFFSET "+start);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                gorev gorev = new gorev();
                gorev.setGorev_id(rs.getInt("gorev_id"));
                gorev.getTip().setTip_id(rs.getInt("tip_id"));
                gorev.setGorev_ad(rs.getString("gorev_ad"));

                gorev.setTip(this.getTipdao().find(rs.getInt("tip_id")));
                gorevListesi.add(gorev);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return gorevListesi;

    }
     public List<gorev> getListAll() {
        List<gorev> gorevListesi = new ArrayList();
   
        try {
            PreparedStatement pst = con.prepareStatement("select *from gorev order by gorev_id");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                gorev gorev = new gorev();
                gorev.setGorev_id(rs.getInt("gorev_id"));
                gorev.getTip().setTip_id(rs.getInt("tip_id"));
                gorev.setGorev_ad(rs.getString("gorev_ad"));

                gorev.setTip(this.getTipdao().find(rs.getInt("tip_id")));
                gorevListesi.add(gorev);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return gorevListesi;

    }

    public void Save(gorev gorev) {
        try {
            PreparedStatement pst = con.prepareStatement("insert into gorev (gorev_ad,tip_id) values(?,?)");
            pst.setString(1, gorev.getGorev_ad());
            pst.setInt(2, gorev.getTip().getTip_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

    }

    public void Delete(gorev gorev) {
        try {
            PreparedStatement pst = con.prepareStatement("delete from gorev where gorev_id=" + gorev.getGorev_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void update(gorev gorev) {
        try {
            Statement st = con.createStatement();
            st.executeUpdate("update gorev set gorev_ad='" + gorev.getGorev_ad() + "' where gorev_id=" + gorev.getGorev_id());
            st.executeUpdate("update gorev set tip_id='" + gorev.getTip().getTip_id() + "' where gorev_id=" + gorev.getGorev_id());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public tipDAO getTipdao() {
        if (this.tipdao == null) {
            this.tipdao = new tipDAO();
        }
        return tipdao;
    }

    public gorev findGorev(int id) {

        gorev g = null;

        try {
            PreparedStatement pst = con.prepareStatement("select * from gorev where gorev_id=" + id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            g = new gorev();
            g.setGorev_id(rs.getInt("gorev_id"));
            g.setGorev_ad(rs.getString("gorev_ad"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return g;

    }

}
