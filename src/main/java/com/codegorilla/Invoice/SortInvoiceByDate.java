package com.codegorilla.Invoice;

import java.util.Comparator;

public class SortInvoiceByDate implements Comparator<Invoice> {
    @Override
    public int compare(Invoice o1, Invoice o2) {
        return o1.getInvoiceDate().compareTo(o2.getInvoiceDate());
    }
}