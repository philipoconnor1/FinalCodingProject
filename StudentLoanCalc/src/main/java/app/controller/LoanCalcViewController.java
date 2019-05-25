package app.controller;

import app.Payment;
import app.StudentCalc;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import org.apache.poi.ss.formula.functions.*;

public class LoanCalcViewController implements Initializable   {

	private StudentCalc SC = null;
	
	@FXML
	private GridPane box;
	
	@FXML
	private GridPane text;
	
	@FXML
	private TextField LoanAmount;
	
	@FXML
	private TextField InterestRate;
	
	@FXML
	private TextField NbrOfYears;
	
	@FXML
	private Label lblTotalPayments;
	
	@FXML
	private Label lblTotalInterests;
	
	@FXML
	private DatePicker PaymentStartDate;
	
	@FXML
	private TextField AdditionalPayment;
	
	private static double TotalPayment;
	
	private static double TotalInterest;
	
	public static void setTotalPayment(double totalPayment) {
		TotalPayment = totalPayment;
	}

	public static void setTotalInterest(double totalInterest) {
		TotalInterest = totalInterest;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	public void setMainApp(StudentCalc sc) {
		this.SC = sc;
	}
	
	 public static Predicate<Node> containsNumbers() {
	        return p -> p.toString().contains(".");
	}
	
	/**
	 * btnCalcLoan - Fire this event when the button clicks
	 * 
	 * @version 1.0
	 * @param event
	 */
	@FXML
	private void btnCalcLoan(ActionEvent event) {
		
		box.getChildren().clear();
		
		double dInterestRate = Double.parseDouble(InterestRate.getText()) / 12;
		double dLoanAmount = Double.parseDouble(LoanAmount.getText());
		double dNbrOfYears = Double.parseDouble(NbrOfYears.getText()) * 12;
		double dAdditionalPayment = Double.parseDouble(AdditionalPayment.getText());
		LocalDate localDate = PaymentStartDate.getValue();
		
		Payment.calculations(dInterestRate, dNbrOfYears, dLoanAmount, dAdditionalPayment);
		
		TotalInterest *= 100;
		long round = Math.round(TotalInterest);
		TotalInterest = (double) round / 100;
		
		TotalPayment *= 100;
		long round2 = Math.round(TotalPayment);
		TotalPayment = (double) round2 / 100;
		
		lblTotalPayments = new Label("$" + Double.toString(TotalPayment));
		lblTotalInterests = new Label("$" + Double.toString(TotalInterest));
		
		box.add(lblTotalPayments, 0, 0);
		box.add(lblTotalInterests, 0, 1);
		
	}
	
}
