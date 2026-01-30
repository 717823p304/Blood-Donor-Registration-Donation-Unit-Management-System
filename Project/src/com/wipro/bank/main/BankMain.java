package com.wipro.bank.main;

import com.wipro.bank.bean.TransferBean;
import com.wipro.bank.service.BankService;

public class BankMain {
   public static void main(String[] args)
   {
	   BankService bankService=new BankService();
	   System.out.println(bankService.checkBalance("2345"));
	   TransferBean transferBean=new TransferBean();
	   transferBean.setFromAccountNumber("2345");
	   transferBean.setAmount(400);
	   transferBean.setToAccountNumber("3456");
	   transferBean.setDateOfTransaction(new java.util.Date());
	   System.out.println(bankService.transfer(transferBean));
   }
}