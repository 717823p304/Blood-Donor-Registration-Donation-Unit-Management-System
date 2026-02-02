package com.bloodbank.service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.bloodbank.bean.Donor;
import com.bloodbank.bean.DonationUnit;
import com.bloodbank.dao.DonorDAO;
import com.bloodbank.dao.DonationUnitDAO;

public class BloodBankService {

    private DonorDAO donorDAO = new DonorDAO();
    private DonationUnitDAO donationUnitDAO = new DonationUnitDAO();

    public boolean registerNewDonor(Donor donor) {

        // ❌ CASE 1: validation failed
        if (donor == null ||
            donor.getDonorId() == null || donor.getDonorId().isEmpty() ||
            donor.getFullName() == null || donor.getFullName().isEmpty() ||
            donor.getBloodGroup() == null || donor.getBloodGroup().isEmpty() ||
            donor.getDateOfBirth() == null ||
            donor.getContactNumber() == null || donor.getContactNumber().isEmpty()) {
            return false;
        }

        // ❌ CASE 2: donor already exists
        if (donorDAO.findDonor(donor.getDonorId()) != null)
            return false;

        // ❌ CASE 3: insertDonor() failed
        donor.setLastDonationDate(null);
        return donorDAO.insertDonor(donor);
    }


    public Donor viewDonorDetails(String donorId) {
        return donorDAO.findDonor(donorId);
    }

    public List<Donor> viewAllDonors() {
        return donorDAO.viewAllDonors();
    }

    public boolean recordDonation(
            String donorId, Date donationDate,
            double volume, Date expiryDate) {

        try {
            Donor donor = donorDAO.findDonor(donorId);
            if (donor == null) return false;

            long age =
              (new Date().getTime() -
               donor.getDateOfBirth().getTime()) /
               (1000L * 60 * 60 * 24 * 365);

            if (age < 18) return false;

            if (donor.getLastDonationDate() != null) {
                long gap =
                  TimeUnit.DAYS.convert(
                    donationDate.getTime() -
                    donor.getLastDonationDate().getTime(),
                    TimeUnit.MILLISECONDS);
                if (gap < 90) return false;
            }

            DonationUnit du = new DonationUnit();
            du.setUnitID(donationUnitDAO.generateUnitID());
            du.setDonorID(donorId);
            du.setBloodGroup(donor.getBloodGroup());
            du.setDonationDate(new java.sql.Date(donationDate.getTime()));
            du.setVolumeML(volume);
            du.setScreeningResult("PENDING");
            du.setExpiryDate(
              new java.sql.Date(expiryDate.getTime()));
            du.setStatus("COLLECTED");

            boolean a = donationUnitDAO.recordDonationUnit(du);
            boolean b = donorDAO.updateLastDonationDate(
              donorId,
              new java.sql.Date(donationDate.getTime()));

            return a && b;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updateScreeningResult(
            int unitID, String result) {

        String status =
            result.equals("PASS") ?
            "AVAILABLE" : "DISCARDED";

        return donationUnitDAO.updateScreeningResult(
            unitID, result, status);
    }

    public boolean markUnitUsedOrDiscarded(
            int unitID, String status) {

        return donationUnitDAO.updateUnitStatus(
            unitID, status);
    }

    public boolean removeDonor(String donorId) {
        List<DonationUnit> list =
            donationUnitDAO.findActiveUnitsByDonor(donorId);
        if (!list.isEmpty())
            return false;

        return donorDAO.deleteDonor(donorId);
    }
}
