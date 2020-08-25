/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Student
 */
public class Passenger {
    
    String FamilyName;
    String GivenName;
    String PasportNo;
    String PassengerCountry;

    public Passenger(String FamilyName, String GivenName, String PasportNo, String PassengerCountry) {
        this.FamilyName = FamilyName;
        this.GivenName = GivenName;
        this.PasportNo = PasportNo;
        this.PassengerCountry = PassengerCountry;
    }

    public String getFamilyName() {
        return FamilyName;
    }

    public void setFamilyName(String FamilyName) {
        this.FamilyName = FamilyName;
    }

    public String getGivenName() {
        return GivenName;
    }

    public void setGivenName(String GivenName) {
        this.GivenName = GivenName;
    }

    public String getPasportNo() {
        return PasportNo;
    }

    public void setPasportNo(String PasportNo) {
        this.PasportNo = PasportNo;
    }

    public String getPassengerCountry() {
        return PassengerCountry;
    }

    public void setPassengerCountry(String PassengerCountry) {
        this.PassengerCountry = PassengerCountry;
    }

    @Override
    public String toString() {
        return "(" + PasportNo + ") " + PassengerCountry + ", " + FamilyName + ", " + GivenName;
//        return "Passenger:  " +  "FamilyName=" + FamilyName + ", GivenName=" + GivenName + ", PasportNo=" + PasportNo + ", PassengerCountry=" + PassengerCountry;
    }
    
    
    
}
