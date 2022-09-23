package com.codegorilla.Invoice;

import java.time.LocalDate;

public class Invoice {
	private LocalDate invoiceDate;
	private double invoiceAmount;

	public Invoice(LocalDate invoiceDate, double invoiceAmount) {
		this.invoiceDate = invoiceDate;
		this.invoiceAmount = invoiceAmount;
     }

     public LocalDate getInvoiceDate() {
    	 return invoiceDate;
     }

     public double getInvoiceAmount() {
    	 return invoiceAmount;
     }

     public void setInvoiceDate(LocalDate invoiceDate) {
    	 this.invoiceDate = invoiceDate;
     }

     public void setInvoiceAmount(double invoiceAmount) {
    	 this.invoiceAmount = invoiceAmount;
     }
}