package com.wipro.bank.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wipro.bank.bean.TransferBean;
import com.wipro.bank.util.DBUtil;

public class BankDAO {

	public int generateSequenceNumber() {
		Connection connection=DBUtil.getDBConnection();
		String query="select transactionId_seq.NEXTVAL from dual";
		try {
			PreparedStatement ps=connection.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			rs.next();
			int seqNumber=rs.getInt(1);
			return seqNumber;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	public boolean validateAccount(String accountNumber) {
		 Connection connection=DBUtil.getDBConnection();
		 String query="select * from ACCOUNT_TBL where Account_Number=?";
		 try {
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setString(1, accountNumber);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			return false;
		}
		return false; 
	}
	public float findBalance(String accountNumber) { 
		if(validateAccount(accountNumber)) {
			String query="select Balance from ACCOUNT_TBL where Account_Number=?";
			try {
				Connection connection=DBUtil.getDBConnection();
				PreparedStatement ps=connection.prepareStatement(query);
				ps.setString(1, accountNumber);
				ResultSet rs=ps.executeQuery();
				rs.next();
				float balance=rs.getFloat(1);
				return balance;
			}catch(SQLException e){
				return -1;
			}
		}
		return -1;
	}
	public boolean transferMoney(TransferBean transferBean) {
		Connection connection=DBUtil.getDBConnection();
		transferBean.setTransactionID(generateSequenceNumber());
		String query="insert into TRANSFER_TBL VALUES(?,?,?,?,?)";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, transferBean.getTransactionID());
			ps.setString(2,transferBean.getFromAccountNumber());
			ps.setString(3,transferBean.getToAccountNumber());
			Date d=new Date(transferBean.getDateOfTransaction().getTime());
			ps.setDate(4, d);
			ps.setFloat(5,transferBean.getAmount());
			int rows=ps.executeUpdate();
			if(rows>0)
				return true;
		} catch (SQLException e) {
			return false;
		}
		return false;
	}
	public boolean updateBalance(String accountNumber,float newBalance) {
		if(validateAccount(accountNumber)) {
			String query="update ACCOUNT_TBL SET Balance=? where Account_Number=?";
			try {
				Connection connection=DBUtil.getDBConnection();
				PreparedStatement ps=connection.prepareStatement(query);
				ps.setFloat(1,newBalance);
				ps.setString(2, accountNumber);
				int rows=ps.executeUpdate();
				if(rows>0)
					return true;
				
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return false;
		
	}
}