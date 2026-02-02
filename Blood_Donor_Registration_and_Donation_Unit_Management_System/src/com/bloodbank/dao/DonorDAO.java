package com.bloodbank.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.bloodbank.bean.Donor;
import com.bloodbank.util.DBUtil;

public class DonorDAO {

    public Donor findDonor(String donorId) {
        Donor donor = null;
        try {
            Connection con = DBUtil.getConnection();
            PreparedStatement ps =
            	    con.prepareStatement("SELECT * FROM DONOR_TBL WHERE DONOR_ID=?");
            ps.setString(1, donorId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                donor = new Donor();
                donor.setDonorId(rs.getString("DONOR_ID"));
                donor.setFullName(rs.getString("FULL_NAME"));
                donor.setBloodGroup(rs.getString("BLOOD_GROUP"));
                donor.setDateOfBirth(rs.getDate("DATE_OF_BIRTH"));
                donor.setGender(rs.getString("GENDER"));
                donor.setLastDonationDate(
                    rs.getDate("LAST_DONATION_DATE"));
                donor.setContactNumber(
                    rs.getString("CONTACT_NUMBER"));
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();   // 🔥 SHOW REAL ERROR
        }
        return donor;
    }

    public List<Donor> viewAllDonors() {
        List<Donor> list = new ArrayList<>();
        try {
            Connection con = DBUtil.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(
                "SELECT * FROM DONOR_TBL");

            while (rs.next()) {
                Donor d = new Donor();
                d.setDonorId(rs.getString("DONOR_ID"));
                d.setFullName(rs.getString("FULL_NAME"));
                d.setBloodGroup(rs.getString("BLOOD_GROUP"));
                d.setDateOfBirth(rs.getDate("DATE_OF_BIRTH"));
                d.setGender(rs.getString("GENDER"));
                d.setLastDonationDate(
                    rs.getDate("LAST_DONATION_DATE"));
                d.setContactNumber(
                    rs.getString("CONTACT_NUMBER"));
                list.add(d);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();   // 🔥 SHOW REAL ERROR
        }
        return list;
    }

    public boolean insertDonor(Donor donor) {
        boolean status = false;
        try {
            Connection con = DBUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(
            	    "INSERT INTO DONOR_TBL " +
            	    "(DONOR_ID, FULL_NAME, BLOOD_GROUP, DATE_OF_BIRTH, GENDER, LAST_DONATION_DATE, CONTACT_NUMBER) " +
            	    "VALUES (?,?,?,?,?,?,?)"
            	);
            ps.setString(1, donor.getDonorId());
            ps.setString(2, donor.getFullName());
            ps.setString(3, donor.getBloodGroup());
            ps.setDate(4, donor.getDateOfBirth());
            ps.setString(5, donor.getGender());
            ps.setDate(6, donor.getLastDonationDate());
            ps.setString(7, donor.getContactNumber());

            status = ps.executeUpdate() > 0;
            con.commit();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();   // 🔥 SHOW REAL ERROR
        }
        return status;
    }

    public boolean updateLastDonationDate(
            String donorId, Date date) {

        boolean status = false;
        try {
            Connection con = DBUtil.getConnection();
            PreparedStatement ps =
                con.prepareStatement(
                  "UPDATE DONOR_TBL SET LAST_DONATION_DATE=? WHERE DONOR_ID=?");
            ps.setDate(1, date);
            ps.setString(2, donorId);

            status = ps.executeUpdate() > 0;
            con.commit();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();   // 🔥 SHOW REAL ERROR
        }
        return status;
    }

    public boolean deleteDonor(String donorId) {
        boolean status = false;
        try {
            Connection con = DBUtil.getConnection();
            PreparedStatement ps =
                con.prepareStatement(
                  "DELETE FROM DONOR_TBL WHERE DONOR_ID=?");
            ps.setString(1, donorId);

            status = ps.executeUpdate() > 0;
            con.commit();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();   // 🔥 SHOW REAL ERROR
        }
        return status;
    }
}
