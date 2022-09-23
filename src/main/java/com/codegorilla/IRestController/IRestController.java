package com.codegorilla.IRestController;

import com.codegorilla.JsonSolar.*;

import java.util.List;  

import org.springframework.web.*;  

@IRestController
public class IRestController {

	@Autowired  
	private IProductService productService;  

	@GetMapping(value = "/invoices")  
	public List<Product> getInvoices()   
	{  
	  
		List<Invoice> listInvoices = JsonSolar.getInvoices();
		
		//returns the product list  
		return products;  
	}  

	
}
