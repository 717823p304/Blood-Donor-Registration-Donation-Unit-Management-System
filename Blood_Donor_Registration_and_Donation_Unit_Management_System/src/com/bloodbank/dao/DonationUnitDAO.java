package com.bloodbank.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.bloodbank.bean.DonationUnit;
import com.bloodbank.util.DBUtil;

public class DonationUnitDAO {

    public int generateUnitID() {
        int id = 0;
        try {
            Connection con = DBUtil.getConnection();
            PreparedStatement ps =
                con.prepareStatement(
                  "SELECT NVL(MAX(UNIT_ID),0)+1 FROM DONATION_UNIT_TBL");
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                id = rs.getInt(1);
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return id;
    }

    public boolean recordDonationUnit(DonationUnit unit) {
        boolean status = false;
        try {
            Connection con = DBUtil.getConnection();
            PreparedStatement ps =
                con.prepareStatement(
                  "INSERT INTO DONATION_UNIT_TBL VALUES (?,?,?,?,?,?,?,?)");
            ps.setInt(1, unit.getUnitID());
            ps.setString(2, unit.getDonorID());
            ps.setString(3, unit.getBloodGroup());
            ps.setDate(4, unit.getDonationDate());
            ps.setDouble(5, unit.getVolumeML());
            ps.setString(6, unit.getScreeningResult());
            ps.setDate(7, unit.getExpiryDate());
            ps.setString(8, unit.getStatus());

            status = ps.executeUpdate() > 0;
            con.commit();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return status;
    }

    public boolean updateScreeningResult(
            int unitID, String result, String status) {

        boolean flag = false;
        try {
            Connection con = DBUtil.getConnection();
            PreparedStatement ps =
                con.prepareStatement(
                  "UPDATE DONATION_UNIT_TBL SET SCREENING_RESULT=?, STATUS=? WHERE UNIT_ID=?");
            ps.setString(1, result);
            ps.setString(2, status);
            ps.setInt(3, unitID);

            flag = ps.executeUpdate() > 0;
            con.commit();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return flag;
    }

    public boolean updateUnitStatus(int unitID, String status) {
        boolean flag = false;
        try {
            Connection con = DBUtil.getConnection();
            PreparedStatement ps =
                con.prepareStatement(
                  "UPDATE DONATION_UNIT_TBL SET STATUS=? WHERE UNIT_ID=?");
            ps.setString(1, status);
            ps.setInt(2, unitID);

            flag = ps.executeUpdate() > 0;
            con.commit();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return flag;
    }

    public List<DonationUnit> findActiveUnitsByDonor(String donorId) {
        List<DonationUnit> list = new ArrayList<>();
        try {
            Connection con = DBUtil.getConnection();
            PreparedStatement ps =
                con.prepareStatement(
                  "SELECT * FROM DONATION_UNIT_TBL WHERE DONOR_ID=? AND STATUS IN ('AVAILABLE','COLLECTED')");
            ps.setString(1, donorId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                DonationUnit du = new DonationUnit();
                du.setUnitID(rs.getInt("UNIT_ID"));
                du.setDonorID(rs.getString("DONOR_ID"));
                du.setBloodGroup(rs.getString("BLOOD_GROUP"));
                du.setDonationDate(rs.getDate("DONATION_DATE"));
                du.setVolumeML(rs.getDouble("VOLUME_ML"));
                du.setScreeningResult(rs.getString("SCREENING_RESULT"));
                du.setExpiryDate(rs.getDate("EXPIRY_DATE"));
                du.setStatus(rs.getString("STATUS"));
                list.add(du);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
}
