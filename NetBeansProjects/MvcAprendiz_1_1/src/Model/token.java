package Model;

public class token {

    public token() {
    }
    
    
    int id, aprendiz_id;
    String tokenNum,tokenName,campus,cityT;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAprendiz_id() {
        return aprendiz_id;
    }

    public void setAprendiz_id(int aprendiz_id) {
        this.aprendiz_id = aprendiz_id;
    }

    public String getTokenNum() {
        return tokenNum;
    }

    public void setTokenNum(String tokenNum) {
        this.tokenNum = tokenNum;
    }

    public String getTokenName() {
        return tokenName;
    }

    public void setTokenName(String tokenName) {
        this.tokenName = tokenName;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getCityT() {
        return cityT;
    }

    public void setCityT(String cityT) {
        this.cityT = cityT;
    }
    
}
