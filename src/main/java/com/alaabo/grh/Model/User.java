package com.alaabo.grh.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name ="users")
public class User {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getNIdenNat() {
        return NIdenNat;
    }

    public void setNIdenNat(int NIdenNat) {
        this.NIdenNat = NIdenNat;
    }

    public int getNbrEnfant() {
        return nbrEnfant;
    }

    public void setNbrEnfant(int nbrEnfant) {
        this.nbrEnfant = nbrEnfant;
    }

    public int getCin() {
        return Cin;
    }

    public void setCin(int cin) {
        Cin = cin;
    }

    public int getCcp() {
        return ccp;
    }

    public void setCcp(int ccp) {
        this.ccp = ccp;
    }

    public int getNCVote() {
        return NCVote;
    }

    public void setNCVote(int NCVote) {
        this.NCVote = NCVote;
    }

    public int getPassport() {
        return passport;
    }

    public void setPassport(int passport) {
        this.passport = passport;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPob() {
        return pob;
    }

    public void setPob(String pob) {
        this.pob = pob;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getWillaya() {
        return willaya;
    }

    public void setWillaya(String willaya) {
        this.willaya = willaya;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getS_familliale() {
        return s_familliale;
    }

    public void setS_familliale(String s_familliale) {
        this.s_familliale = s_familliale;
    }

    public String getNSS() {
        return NSS;
    }

    public void setNSS(String NSS) {
        this.NSS = NSS;
    }

    public String getBurRec() {
        return BurRec;
    }

    public void setBurRec(String burRec) {
        BurRec = burRec;
    }

    public String getBurVote() {
        return BurVote;
    }

    public void setBurVote(String burVote) {
        BurVote = burVote;
    }

    public Date getGradDepuis() {
        return GradDepuis;
    }

    public void setGradDepuis(Date gradDepuis) {
        GradDepuis = gradDepuis;
    }

    public Date getFonctionDepuis() {
        return FonctionDepuis;
    }

    public void setFonctionDepuis(Date fonctionDepuis) {
        FonctionDepuis = fonctionDepuis;
    }

    public Date getDateRecr() {
        return DateRecr;
    }

    public void setDateRecr(Date dateRecr) {
        DateRecr = dateRecr;
    }

    public Date getArriverLe() {
        return arriverLe;
    }

    public void setArriverLe(Date arriverLe) {
        this.arriverLe = arriverLe;
    }

    public Date getQuiterLe() {
        return QuiterLe;
    }

    public void setQuiterLe(Date quiterLe) {
        QuiterLe = quiterLe;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public Date getCNDelivrerLe() {
        return CNDelivrerLe;
    }

    public void setCNDelivrerLe(Date CNDelivrerLe) {
        this.CNDelivrerLe = CNDelivrerLe;
    }

    public Date getCVDelivrerLe() {
        return CVDelivrerLe;
    }

    public void setCVDelivrerLe(Date CVDelivrerLe) {
        this.CVDelivrerLe = CVDelivrerLe;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int serviceId ,NIdenNat , nbrEnfant , Cin , ccp,NCVote , passport ,telephone;
    private String nom , prenom ,matricule , grade , fonction  , category , pob , adress , willaya , sex , s_familliale ,  NSS , BurRec , BurVote;
    private Date GradDepuis ,FonctionDepuis , DateRecr , arriverLe , QuiterLe ,DOB ,CNDelivrerLe , CVDelivrerLe;

}
