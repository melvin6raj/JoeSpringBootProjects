package com.melvin.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
		//CRUD Operations
	@Autowired
	private EmployeeRepository employeeRepository;

	@PostMapping
	public ResponseEntity<Employee> registerEmployee(@RequestBody Employee emp) {
		Employee e = employeeRepository.save(emp);
		return new ResponseEntity<Employee>(e, HttpStatus.CREATED);
	}
	
	
	@GetMapping
	public ResponseEntity<List<Employee>> fetchAllEmployee() {
		List<Employee> e = (List<Employee>) employeeRepository.findAll();
		return new ResponseEntity<List<Employee>>(e, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Employee> fetchOneEmployee(@PathVariable Integer id) {
		return new ResponseEntity<Employee>(employeeRepository.findById(id).get(), HttpStatus.CREATED);
	}
	
   @PutMapping
	public  ResponseEntity<Employee>  updateEmployee(@RequestBody Employee emp) {
	   Employee e1 = employeeRepository.findById(emp.getEmpId()).get();
	   e1.setEmpId(emp.getEmpId());
	   e1.setEmpName(emp.getEmpName());
	   Employee e2 = employeeRepository.save(e1);
	   return new ResponseEntity<Employee>(e2, HttpStatus.CREATED); 

	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteOneEmployee(@PathVariable Integer id) {
		employeeRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.GONE);
	}
	
	
	
}
