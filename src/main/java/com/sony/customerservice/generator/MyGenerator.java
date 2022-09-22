package com.sony.customerservice.generator;
import com.sony.customerservice.entity.Customer;

public class MyGenerator 
{
	public static Customer sendForIdGeneration(Customer customer) 
	{
		String companyName = customer.getCompanyName();
		String[] arr = companyName.split(" ");
		String first4 = arr[0].substring(0, 4);
		String last1 = arr[1].substring(0, 1);
		customer.setCustomerId((first4+last1).toUpperCase());
		return customer;
	}
}

