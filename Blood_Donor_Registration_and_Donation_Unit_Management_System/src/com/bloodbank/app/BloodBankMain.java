package com.bloodbank.app;

import java.sql.Date;
import com.bloodbank.bean.Donor;
import com.bloodbank.service.BloodBankService;

public class BloodBankMain {

    public static void main(String[] args) {

        BloodBankService service = new BloodBankService();

        Donor d = new Donor();
        d.setDonorId("DNR5002");
        d.setFullName("Anu Reka");
        d.setBloodGroup("A+");
        d.setDateOfBirth(Date.valueOf("2002-05-10"));
        d.setGender("FEMALE");
        d.setContactNumber("9999999999");

        boolean result = service.registerNewDonor(d);

        if (result)
            System.out.println("DONOR REGISTERED SUCCESSFULLY");
        else
            System.out.println("DONOR REGISTRATION FAILED");
    }
}