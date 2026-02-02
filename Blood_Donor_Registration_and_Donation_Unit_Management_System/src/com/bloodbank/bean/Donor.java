package com.bloodbank.bean;

import java.sql.Date;

public class Donor {

    private String donorId;
    private String fullName;
    private String bloodGroup;
    private Date dateOfBirth;
    private String gender;
    private Date lastDonationDate;
    private String contactNumber;

    public String getDonorId() {
        return donorId;
    }
    public void setDonorId(String donorId) {
        this.donorId = donorId;
    }

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }
    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getLastDonationDate() {
        return lastDonationDate;
    }
    public void setLastDonationDate(Date lastDonationDate) {
        this.lastDonationDate = lastDonationDate;
    }

    public String getContactNumber() {
        return contactNumber;
    }
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
