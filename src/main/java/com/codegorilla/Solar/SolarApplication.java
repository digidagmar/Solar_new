package com.codegorilla.Solar;

import com.codegorilla.JsonSolar.JsonSolar;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class SolarApplication {

	static JsonSolar solar;

	public static void main(String[] args) {
		solar = new JsonSolar();
		SpringApplication.run(SolarApplication.class, args);
	}

	@PostMapping("/invoices/create")
	public String createInvoice(@RequestBody String jsonBody) {
		String retval;
		retval = JsonSolar.createJsonInvoice( jsonBody );
		return retval;
	}

	@PostMapping("/invoices/delete")
	public String deleteInvoice(@RequestBody String jsonBody) {
		String retval;
		retval = JsonSolar.deleteJsonInvoice( jsonBody );
		return retval;
	}

	@PostMapping("/invoices/update")
	public String updateInvoice(@RequestBody String jsonBody) {
		String retval;
		retval = JsonSolar.updateJsonInvoice( jsonBody );
		return retval;
	}

	@GetMapping("/invoices/get")
	public String getInvoices(@RequestParam(value = "myName", defaultValue = "World") String name) {
		return JsonSolar.parseJsonInvoices(JsonSolar.getInvoices());
	}


}
