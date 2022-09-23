package com.codegorilla.Measurement;

import com.codegorilla.Invoice.*;


import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.*;

public class RegisterMeasurements {
	
	public static Measurement inputMeasurements() 
	{

		Scanner sc = new Scanner(System.in);

		System.out.println("Insert date dd-mm-yyyy");
		LocalDate insertDate = verifyDateMeasurement(sc.nextLine());
		System.out.println("Insert power generated");
		double powerGenerated = verifyMeasurement(sc.nextLine());
		System.out.println("Insert power consumed");
		double powerConsumption = verifyMeasurement(sc.nextLine());
		System.out.println("Insert power returned");
		double powerReturn = verifyMeasurement(sc.nextLine());
		return new Measurement( insertDate, powerGenerated, powerConsumption, powerReturn );
	}

    public static Invoice inputInvoice() 
    {

    	Scanner sc = new Scanner(System.in);

    	System.out.println("Insert date MM-yyyy");
    	LocalDate invoiceMonthAndYear = verifyDateInvoice(sc.nextLine());
    	System.out.println("Insert monthly invoice");
    	double invoiceAmount = verifyInvoice(sc.nextLine());
    	return new Invoice(invoiceMonthAndYear, invoiceAmount);

    }

    public static double verifyMeasurement(String input) {
    	Scanner sc = new Scanner(input);
    	
    	if (input.equals("")) {
    		return 0;
    	} else if (!sc.hasNextDouble() || sc.nextDouble() < 0) {
    		System.out.println("invalid entry");
    		Scanner sc2 = new Scanner(System.in);
    		return verifyMeasurement(sc2.nextLine());
    	} else {
        	DecimalFormat dfZero = new DecimalFormat("#.##");
        	return Double.parseDouble(dfZero.format((Double.parseDouble(input))));
    	}
	}

    public static double verifyInvoice(String input) 
    {
    	Scanner sc = new Scanner(input);
    	
    	if (input.equals("")) {
    		return 0;
    	} else if (!sc.hasNextDouble()) {
    		System.out.println("invalid entry");
    		Scanner sc2 = new Scanner(System.in);
    		return verifyInvoice(sc2.nextLine());
    	} else {
    		DecimalFormat dfZero = new DecimalFormat("#.##");
    		return Double.parseDouble(dfZero.format((Double.parseDouble(input))));
    	}
	}


	public static LocalDate verifyDateMeasurement(String input) 
	{
		try {
			DateTimeFormatter formatter = new DateTimeFormatterBuilder()
					.appendPattern("dd-MM-yyyy")
					.toFormatter(Locale.GERMAN);
			LocalDate date = LocalDate.parse(input, formatter);
			LocalDate todayDate = LocalDate.now();
			
			if (date.isBefore(todayDate) || date.isEqual(todayDate)) {
				return date;
			} else {
				System.out.println("invalid entry Date in the future");
				Scanner sc = new Scanner(System.in);
				return verifyDateMeasurement(sc.nextLine());
			}
		} catch (Exception e) {
			System.out.println("invalid entry");
			Scanner sc = new Scanner(System.in);
			return verifyDateMeasurement(sc.nextLine());
		}
	}
	
	public static LocalDate verifyDateInvoice(String input) 
	{
		
		try {
			DateTimeFormatter formatter = new DateTimeFormatterBuilder()
					.appendPattern("MM-yyyy")
					.parseDefaulting(ChronoField.DAY_OF_MONTH, 01)
					.toFormatter(Locale.GERMAN);
			LocalDate date = LocalDate.parse(input, formatter);
			LocalDate todayDate = LocalDate.now();
			
			if (date.isBefore(todayDate) || date.isEqual(todayDate)) {
				return date;
			} else {
				System.out.println("invalid entry Date in the future");
				Scanner sc = new Scanner(System.in);
				return verifyDateInvoice(sc.nextLine());
			}
		} catch (Exception e) {
			System.out.println("invalid entry");
			Scanner sc = new Scanner(System.in);
			return verifyDateInvoice(sc.nextLine());
		}
	}
}




