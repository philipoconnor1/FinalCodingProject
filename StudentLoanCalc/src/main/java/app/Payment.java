package app;

import org.apache.poi.ss.formula.functions.FinanceLib;

import app.controller.LoanCalcViewController;

public class Payment {

	public static void calculations(double rate, double years, double loan, double additional) {
		
		double dMonthly = Math.abs(FinanceLib.pmt(rate, years, loan, 0, false));
		dMonthly *= 100;
	    long tmp = Math.round(dMonthly);
	    dMonthly = (double) tmp / 100;
	    dMonthly = dMonthly + additional;
	    
	    double balance = loan;
	    double totalint = 0;
	    double totalprin = 0;
	    double total = 0;
	    do {
	    	if(dMonthly > balance) {
	    		double InterestPaid = rate * balance;
				InterestPaid *= 100;
				long round = Math.round(InterestPaid);
				InterestPaid = (double) round / 100;
				
				double PrincipalPaid = balance;
				balance = 0;
				totalint = totalint + InterestPaid;
				totalprin = totalprin + PrincipalPaid;
				total = total + InterestPaid + PrincipalPaid;
			} else {
				double InterestPaid = rate * balance;
				InterestPaid *= 100;
				long round = Math.round(InterestPaid);
				InterestPaid = (double) round / 100;
				
				double PrincipalPaid = dMonthly - InterestPaid;
				balance = balance - PrincipalPaid;
				totalint = totalint + InterestPaid;
				totalprin = totalprin + PrincipalPaid;
				total = total + InterestPaid + PrincipalPaid;
			}
	    } while(balance != 0);
		LoanCalcViewController.setTotalPayment(total);
		LoanCalcViewController.setTotalInterest(totalint);
	}
	
}
