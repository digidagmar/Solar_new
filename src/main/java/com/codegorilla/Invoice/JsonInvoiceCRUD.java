package com.codegorilla.Invoice;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.*;
import org.json.simple.parser.*;

public class JsonInvoiceCRUD implements InvoiceCRUD {

	private List<Invoice> invoiceList;

	public JsonInvoiceCRUD() {
	//	invoiceList = Json.getJsonInvoices();
		if (invoiceList == null) {
			invoiceList = new ArrayList<>();
		}
    }

    @Override
    public void create(Invoice invoice) {
        if (getIndex(invoice) == -1) {
        	invoiceList.add(invoice);
        } else {
            System.out.println("Invoice with this date already exists. Nothing created.");
        }
    }

    @Override
    public List<Invoice> read() {
        return invoiceList;
    }

    @Override
    public void update(Invoice invoice) {
        try {
        	invoiceList.set(getIndex(invoice),invoice);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invoice not found. Nothing updated.");
        }
    }

    @Override
    public void delete(Invoice invoice) {
        int index = getIndex(invoice);
        if (index == -1) {
            System.out.println("Index not found. Nothing deleted.");
        } else {
        	invoiceList.remove(index);
        }
    }

    public void write() {
    	// TODO
    	// Json.putJsonInvoices(array);
    }

    private int getIndex(Invoice invoice) {
        int index = -1;
        for (int i = 0; i < invoiceList.size(); i++) {
            if (invoiceList.get(i).getInvoiceDate().isEqual(invoice.getInvoiceDate())){
                index = i;
                break;
            }
        }
        return index;
    }
}
