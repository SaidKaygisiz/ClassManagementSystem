package Dao;

import Entity.grup;
import Util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class grupDAO {

    DBConnection db = new DBConnection();
    Connection con = db.connect();

    public grup find(int id) {
        grup g = null;

        try {
            PreparedStatement pst = con.prepareStatement("select * from grup where grup_id=" + id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            g = new grup();
            g.setGrup_id(rs.getInt("grup_id"));
            g.setGrup_ad(rs.getString("grup_ad"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return g;
    }

    public List<grup> getList() {
        List<grup> grupListesi = new ArrayList();

        try {
            PreparedStatement pst = con.prepareStatement("select * from grup order by grup_id");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                grup grup = new grup();
                grup.setGrup_id((rs.getInt("grup_id")));
                grup.setGrup_ad(rs.getString("grup_ad"));
                grupListesi.add(grup);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return grupListesi;
    }

    public void Save(grup grup) {
        try {
            PreparedStatement pst = con.prepareStatement("insert into grup (grup_ad) values(?)");
            pst.setString(1, grup.getGrup_ad());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }

    public void delete(grup grup) {
        try {
            PreparedStatement pst = con.prepareStatement("delete from grup where grup_id=" + grup.getGrup_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void update(grup grup) {
        try {
            Statement st = con.createStatement();
            st.executeUpdate("update grup set grup_ad='" + grup.getGrup_ad() + "' where grup_id=" + grup.getGrup_id());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
