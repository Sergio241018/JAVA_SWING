package Model;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class AprendizDAO {

    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conectionn connect = Conectionn.getInstance();

    public List Listar() {
        List<Apprentice> datos = new ArrayList<>();
        try {
            con = connect.connect();
            ps = con.prepareStatement("SELECT * FROM aprendiz");
            rs = ps.executeQuery();
            while (rs.next()) {
                Apprentice ap = new Apprentice();
                ap.setId(rs.getInt(1));
                ap.setTipedoc(rs.getString(2));
                ap.setNumerdoc(rs.getString(3));
                ap.setName(rs.getString(4));
                ap.setDate(rs.getDate(5));
                ap.setGender(rs.getString(6));
                ap.setCity(rs.getString(7));
                datos.add(ap);
            }
            
        } catch (SQLException e) {
        }
        return datos;
    }

    public int Add(Apprentice appre) {
        int r = 0;
        String sql = "INSERT INTO aprendiz(doctype,docnumber,name,birthdate,gender,city) VALUES (?,?,?,?,?,?)";
        try {
            con = connect.connect();
            ps = con.prepareStatement(sql);
            ps.setString(1, getdocTypeIndex(appre.getTipedoc()));
            ps.setString(2, appre.getNumerdoc());
            ps.setString(3, appre.getName());
            java.util.Date date = appre.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String format = sdf.format(date);
            ps.setString(4, format);
            ps.setString(5, appre.getGender());
            ps.setString(6, appre.getCity());
            r = ps.executeUpdate();

        } catch (SQLException e) {
        }
        return r;
    }

    public int Update(Apprentice appre) {
        int r = 0;
        String sql = "UPDATE aprendiz SET doctype=? , docnumber=?, name=?, birthdate=?, gender=?, city=? WHERE id=?";
        try {
            con = connect.connect();
            ps = con.prepareStatement(sql);
            ps.setString(1, getdocTypeIndex(appre.getTipedoc()));
            ps.setString(2, appre.getNumerdoc());
            ps.setString(3, appre.getName());
            java.util.Date date = appre.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String format = sdf.format(date);
            ps.setString(4, format);
            ps.setString(5, appre.getGender());
            ps.setString(6, appre.getCity());
            ps.setInt(7, appre.getId());

            r = ps.executeUpdate();
            if (r == 1) {
                return 1;
            } else {
                return 0;
            }

        } catch (SQLException e) {
        }
        return r;
    }

    public int Delete(int ida) {
        int r = 0;
        String sql = "DELETE FROM aprendiz WHERE id=" + ida;
        try {
            con = connect.connect();
            ps = con.prepareStatement(sql);
            r = ps.executeUpdate();
        } catch (SQLException e) {
        }
        return r;
    }

    private String getdocTypeIndex(String doctype) {
        return switch (doctype) {
            case "citizenship card" ->
                "CC";
            case "identity card" ->
                "TI";
            case "Foreigner ID" ->
                "CE";
            case "civil registration" ->
                "RC";
            case "Passport" ->
                "PS";
            default ->
                "";
        };
    }
}
