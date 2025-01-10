package com.db2csv.service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db2csv.entity.Customer;
import com.db2csv.repository.CustomerRepository;

@Service
public class CsvExportService {

    @Autowired
    private CustomerRepository customerRepository;

    public void exportCustomersToCsv(String filePath) throws IOException {
        List<Customer> customers = customerRepository.findAll();

        // Create a writer object
        Writer writer = new FileWriter(filePath);
        
        // Define the CSV format (you can customize headers)
        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("ID", 
        		"Name", "Contact", "Email","Date of Purchase","Customer","Amount"));

        // Write the customer data into the CSV
        for (Customer customer : customers) {
            csvPrinter.printRecord(customer.getId(), customer.getName(), customer.getContact(),customer.getEmail(), customer.getDop(),
            		customer.isCustomer(),customer.getAmount());
        }

        // Close the writer and CSVPrinter
        csvPrinter.flush();
        csvPrinter.close();
    }

}
