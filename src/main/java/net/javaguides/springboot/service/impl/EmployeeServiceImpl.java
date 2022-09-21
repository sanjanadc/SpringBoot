package net.javaguides.springboot.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;
import net.javaguides.springboot.service.EmployeeService;


@Service
public class EmployeeServiceImpl implements EmployeeService{

	private EmployeeRepository employeeRepository;
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEployees() {
	   return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeByID(long id) {
		
		Optional<Employee> employee = employeeRepository.findById(id);
	    if(employee.isPresent())
	    {
	    	return employee.get();
	    }
	    else {
			throw new ResourceNotFoundException("Employee", "ID", id);
			}
	  //Return employeeRepositoryReturn.findById(id).orElseThrow(()-> 
        //new ResourceNotFoundException("Employee", "ID", id));
	}

	@Override
	public Employee updateEmployee(Employee employee, long id) {
		
		// check whether employee with given iD exists
		
		Employee existingEmployee = employeeRepository.findById(id).orElseThrow(
				()-> new ResourceNotFoundException("Employee","ID","id"));
		
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());
		
		// save existing employee to DB
		
		employeeRepository.save(existingEmployee);
		return existingEmployee;
		
	}

	@Override
	public void Delete(long id) {
		
		// check whether employee exists in DB
		employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee","ID","id"));
		
		employeeRepository.deleteById(id);
	}
	
}
 