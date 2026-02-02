package com.bloodbank.bean;

import java.sql.Date;

public class DonationUnit {

    private int unitID;
    private String donorID;
    private String bloodGroup;
    private Date donationDate;
    private double volumeML;
    private String screeningResult;
    private Date expiryDate;
    private String status;

    public int getUnitID() {
        return unitID;
    }
    public void setUnitID(int unitID) {
        this.unitID = unitID;
    }

    public String getDonorID() {
        return donorID;
    }
    public void setDonorID(String donorID) {
        this.donorID = donorID;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }
    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public Date getDonationDate() {
        return donationDate;
    }
    public void setDonationDate(Date donationDate) {
        this.donationDate = donationDate;
    }

    public double getVolumeML() {
        return volumeML;
    }
    public void setVolumeML(double volumeML) {
        this.volumeML = volumeML;
    }

    public String getScreeningResult() {
        return screeningResult;
    }
    public void setScreeningResult(String screeningResult) {
        this.screeningResult = screeningResult;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
