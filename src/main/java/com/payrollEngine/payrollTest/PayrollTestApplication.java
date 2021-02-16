package com.payrollEngine.payrollTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payrollEngine.payrollTest.Employee.Employee;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class PayrollTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayrollTestApplication.class, args);
	}
    
}
