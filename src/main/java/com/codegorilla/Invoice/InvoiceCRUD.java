package com.codegorilla.Invoice;

import java.util.List;

public interface InvoiceCRUD {
	void create(Invoice invoice);
	List<Invoice> read();
	void update(Invoice invoice);
	void delete(Invoice invoice);
}