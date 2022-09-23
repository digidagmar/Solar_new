
package com.codegorilla.JsonSolar;

import com.codegorilla.Invoice.*;
import com.codegorilla.Measurement.*;

import org.json.simple.*;
import org.json.simple.parser.*;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;



public class JsonSolar {
    static final String MEASUREMENTS_PATH = "/CodeGorilla/measurements.json";
    static final String INVOICES_PATH = "/CodeGorilla/invoices.json";

	private static ArrayList<Invoice> invoiceArr;
	private static ArrayList<Measurement> measurementArr;


	public JsonSolar() {
		invoiceArr = new ArrayList<Invoice>();
		measurementArr = new ArrayList<Measurement>();
	}
	
	public static void addInvoice( Invoice arg )
	{
		invoiceArr.add(arg);
	}
	
	public static void addMeasurement( Measurement arg )
	{
		measurementArr.add(arg);		
	}
	
	public static ArrayList<Invoice> getInvoices( )
	{
		return invoiceArr;
	}
	
	public static ArrayList<Measurement> getMeasurements( )
	{
		return measurementArr;		
	}
	
	
    public static String parseJsonInvoices( ArrayList<Invoice> list )
    {
    	
		String json = "";

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		json = "{";
		json += "\"invoices\": [ ";

		if( list != null ) {
			for (Invoice record : list) {
				json += "{ \"insert_date\" : ";
				json += "\"" + record.getInvoiceDate().format(formatter) + "\", ";
				json += "\"amount\" : ";
				json += record.getInvoiceAmount() + " } ,";
			}
			if (list.size() > 0) {
				json = json.substring(0, json.length() - 1);
			}
		}
		json += " ] } ";

		
		return json;
    	
    }

	public static String createInvoice( String jsonString )
	{
		// TODO

		Invoice tmp;
		tmp = new Invoice( LocalDate.parse("2012-06-09"), 345.89 );
		invoiceArr.add(tmp);
		return "Invoice Added";
	}
    public static String parseJsonMeasurements( ArrayList<Measurement> list )
    {
		
		String json = "";
		
		json = "{";
		json += "\"measurements\": [ ";
		
		for( Measurement record: list ) {
			json += "\"insert_date\" : ";
			json += "\"" + record.getInsertDate() + "\", ";
			json += "\"power_generated\" : ";
			json += record.getPowerGenerated() + ",";
			json += "\"power_consumption\" : ";
			json += record.getPowerConsumption() + ",";
			json += "\"power_return\" : ";
			json += record.getPowerReturn() + ",";
		}
		if( list.size() > 0 ) {
			json = json.substring(0, json.length() - 1);
		}
		json += " ] } ";
	
		
		return json;
	
    }

	public static String createJsonInvoice( String jsonStr )
	{
		System.out.println("Json String: " + jsonStr );

		JSONParser parser = new JSONParser();
		JSONObject json;

		try {
			json = (JSONObject) parser.parse(jsonStr);

		} catch (Exception e ) {
			return "Could not parse JSON";
		}

		String invoiceDate;
		invoiceDate = json.get("insert_date").toString();

		String invoiceAmount;
		invoiceAmount = json.get("amount").toString();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		Invoice temp;

		temp = new Invoice(
				LocalDate.parse( invoiceDate, formatter),
				Double.parseDouble( invoiceAmount )
		);

		ArrayList<Invoice> invoices;

		invoices =  JsonSolar.getInvoices();
		for( int i = 0; i < invoices.size(); i++ ) {
			if (invoices.get(i).getInvoiceDate().compareTo(temp.getInvoiceDate()) == 0 ) {
				return "Invoice insert date already exists.";
			}
		}

		addInvoice(temp);

		return "Invoice Added.";
	}

	public static String deleteJsonInvoice( String jsonStr )
	{
		System.out.println("Json String: " + jsonStr );

		JSONParser parser = new JSONParser();
		JSONObject json;

		try {
			json = (JSONObject) parser.parse(jsonStr);

		} catch (Exception e ) {
			return "Could not parse JSON";
		}

		String invoiceDate;
		invoiceDate = json.get("insert_date").toString();

		String invoiceAmount;
		invoiceAmount = json.get("amount").toString();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		Invoice temp;

		temp = new Invoice(
				LocalDate.parse( invoiceDate, formatter),
				Double.parseDouble( invoiceAmount )
		);

		ArrayList<Invoice> invoices;

		invoices =  JsonSolar.getInvoices();
		for( int i = 0; i < invoices.size(); i++ ) {
			if (invoices.get(i).getInvoiceDate().compareTo(temp.getInvoiceDate()) == 0 ) {
				invoiceArr.remove(i);
				return "Invoice deleted";
			}
		}

		return "Invoice does not exists.";
	}

	public static String updateJsonInvoice( String jsonStr )
	{
		System.out.println("Json String: " + jsonStr );

		JSONParser parser = new JSONParser();
		JSONObject json;

		try {
			json = (JSONObject) parser.parse(jsonStr);

		} catch (Exception e ) {
			return "Could not parse JSON";
		}

		String invoiceDate;
		invoiceDate = json.get("insert_date").toString();

		String invoiceAmount;
		invoiceAmount = json.get("amount").toString();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		Invoice temp;

		temp = new Invoice(
				LocalDate.parse( invoiceDate, formatter),
				Double.parseDouble( invoiceAmount )
		);

		ArrayList<Invoice> invoices;

		invoices =  JsonSolar.getInvoices();
		for( int i = 0; i < invoices.size(); i++ ) {
			if (invoices.get(i).getInvoiceDate().compareTo(temp.getInvoiceDate()) == 0 ) {
				Invoice replaceVar = invoices.get(i);
				replaceVar.setInvoiceAmount(Double.parseDouble(invoiceAmount));
				invoiceArr.remove(i);
				invoiceArr.add(replaceVar);
				return "Invoice updated";
			}
		}

		return "Invoice does not exists.";
	}


	private static void storeJsonInvoice(ArrayList<Invoice> list)
    {
    	String filePath;
    	String jsonText = "";
    	
    	filePath = JsonSolar.INVOICES_PATH;
    	jsonText = parseJsonInvoices( list ); 
    	    	
    	File file = new File(filePath);
    	
    	FileOutputStream outStream;
		PrintStream printStream = null;
		
		try {
			outStream = new FileOutputStream( file, true );
			printStream = new PrintStream( outStream );
		} catch (FileNotFoundException e) {
			System.out.println( "FATAL: Could not open server log file: " + filePath );
			System.exit(0);
		}
		if( !file.canRead() || !file.canWrite() ) {
			System.out.println( "FATAL: Could not open server log file: " + filePath );
			System.exit(0);
		}
		try {
			printStream.print(jsonText);
			printStream.close();
		} catch ( NullPointerException e) {

		}
    	
    }
    
    private static void storeJsonMeasurement(ArrayList<Measurement> list)
    {    	   	
    	String filePath;
    	String jsonText = "";
    	
   		filePath = JsonSolar.MEASUREMENTS_PATH;
   		jsonText = parseJsonMeasurements( list ); 
    	
    	File file = new File(filePath);
    	
    	FileOutputStream outStream;
		PrintStream printStream = null;
		
		try {
			outStream = new FileOutputStream( file, true );
			printStream = new PrintStream( outStream );
		} catch (FileNotFoundException e) {
			System.out.println( "FATAL: Could not open server log file: " + filePath );
			System.exit(0);
		}
		if( !file.canRead() || !file.canWrite() ) {
			System.out.println( "FATAL: Could not open server log file: " + filePath );
			System.exit(0);
		}
		try {
			printStream.print(jsonText);
			printStream.close();
		} catch ( NullPointerException e) {

		}
    	
    }

    public static String readAllBytes(String filePath) 
    {
		
		File file;
		file = new File(filePath);
		
		if( file.exists() == false ) {
			System.out.println("Status file error.");
			return "";
		}
        StringBuilder contentBuilder = new StringBuilder();
        try  
        {
        	BufferedReader br = new BufferedReader(new FileReader(filePath));
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) 
            {
                contentBuilder.append(sCurrentLine).append("\n");
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }
    
    private static void readJsonInvoice(ArrayList<Invoice> list)
    {
    	String filePath;
    	String fileData;
    	String jsonTag;
    	
    	filePath = JsonSolar.INVOICES_PATH;
    	jsonTag = "invoices";
    	fileData = readAllBytes( filePath );    		
    	
		JSONParser parser = new JSONParser();
		JSONObject json = null;	

		try {
			json = (JSONObject) parser.parse(fileData);
			
		} catch (Exception e ) {
			System.out.println("Invalid JSON read from file: " + filePath);
			System.exit(0);
		}

		JSONObject systemData;

		systemData = (JSONObject)json;

		JSONArray items;
		
		items = (JSONArray)systemData.get(jsonTag);
		
		ArrayList<JSONObject> listData = new ArrayList<JSONObject>(items);

		
		for( JSONObject tmp: listData ) {
			Invoice tmpInvoice;
			tmpInvoice = new Invoice(LocalDate.parse(tmp.get("insert_date").toString()), 
					Double.parseDouble( tmp.get("amount").toString() ) 
				);
			JsonSolar.addInvoice(tmpInvoice );
			
		}
		

    }
    private static void readJsonMeasurement(ArrayList<Measurement> list)
    {
    	String filePath;
    	String fileData;
    	String jsonTag;
    	
   		filePath = JsonSolar.MEASUREMENTS_PATH;
   		jsonTag = "measurements";
    	
		fileData = readAllBytes( filePath );    		
    	
		JSONParser parser = new JSONParser();
		JSONObject json = null;	


		try {
			json = (JSONObject) parser.parse(fileData);
			
		} catch (Exception e ) {
			System.out.println("Invalid JSON read from file: " + filePath);
			System.exit(0);
		}

		JSONObject systemData;

		systemData = (JSONObject)json;

		JSONArray items;
		
		items = (JSONArray)systemData.get(jsonTag);
		
		ArrayList<JSONObject> listData = new ArrayList<JSONObject>(items);

		
		for( JSONObject tmp: listData ) {
			Measurement tmpMeasurement;
			tmpMeasurement = new Measurement(LocalDate.parse(tmp.get("insert_date").toString()), 
					Double.parseDouble( tmp.get("power_generated").toString()),
					Double.parseDouble( tmp.get("power_consumption").toString()),
					Double.parseDouble( tmp.get("power_return" ).toString() )
				);
			addMeasurement(tmpMeasurement );
		
		}

    }
    

}


