
package Model;

import java.util.Date;

public class Apprentice {
    
    int id;
    String tipedoc,numerdoc,name,gender,city,tokenNumber,tokenName,campus,CityT;
    Date date;
    
    public Apprentice() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTokenNumber() {
        return tokenNumber;
    }

    public void setTokenNumber(String tokenNumber) {
        this.tokenNumber = tokenNumber;
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

    public String getTipedoc() {
        return tipedoc;
    }

    public void setTipedoc(String tipedoc) {
        this.tipedoc = tipedoc;
    }

    public String getNumerdoc() {
        return numerdoc;
    }

    public void setNumerdoc(String numerdoc) {
        this.numerdoc = numerdoc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityT() {
        return CityT;
    }

    public void setCityT(String CityT) {
        this.CityT = CityT;
    }
    
}
