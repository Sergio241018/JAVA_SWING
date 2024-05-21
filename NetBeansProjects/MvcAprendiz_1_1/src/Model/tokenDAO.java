package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class tokenDAO {
    
    PreparedStatement pss;
    ResultSet rss;
    Connection conn;
    Conectionn connectt = Conectionn.getInstance();
    
    public List ToList(){
        List<token> data = new ArrayList<>();
        try {
            conn = connectt.connect();
            pss = conn.prepareStatement("SELECT * FROM token JOIN aprendiz ON token.aprendiz_id = aprendiz.id ");
            rss = pss.executeQuery();
            while (rss.next()) {
                token to = new token();
                to.setId(rss.getInt(1));
                to.setTokenNum(rss.getString(2));
                to.setTokenName(rss.getString(3));
                to.setCampus(rss.getString(4));
                to.setCityT(rss.getString(5));
                to.setAprendiz_id(rss.getInt(6));
                data.add(to);
            }
        } catch (SQLException e) {
        }
        return data;
    }
    
    public int Add(token tok){
        int r = 0;
        String sql = "INSERT INTO token (num_token,token_name,campus,city,aprendiz_id) VALUES (?,?,?,?,?)";
        try {
            conn = connectt.connect();
            pss = conn.prepareStatement(sql);
            pss.setString(1, tok.getTokenNum());
            pss.setString(2, tok.getTokenName());
            pss.setString(3, tok.getCampus());
            pss.setString(4, tok.getCityT());
            pss.setInt(5, tok.getAprendiz_id());
            r = pss.executeUpdate();
        } catch (SQLException e) {
        }
        return r;
    }
    
    public int Update(token tok){
        int r = 0;
        String sql = "UPDATE token SET num_token=? , token_name=?, campus=?, city=?, aprendiz_id=? WHERE idtoken=?";
        try {
            conn = connectt.connect();
            pss = conn.prepareStatement(sql);
            pss.setString(1, tok.getTokenNum());
            pss.setString(2, tok.getTokenName());
            pss.setString(3, tok.getCampus());
            pss.setString(4, tok.getCityT());
            pss.setInt(5, tok.getAprendiz_id());
            pss.setInt(6, tok.getId());
            r = pss.executeUpdate();
            if (r == 1) {
                return 1;
            } else {
                return 0;
            }
        } catch (SQLException e) {
        }
        return r;
    }
    public int Delete(int ida){
        int r = 0;
        String sql = "DELETE FROM token WHERE idtoken=" + ida;
        try {
            conn = connectt.connect();
            pss = conn.prepareStatement(sql);
            r = pss.executeUpdate();
        } catch (SQLException e) {
        }
        return r;
    }
}
