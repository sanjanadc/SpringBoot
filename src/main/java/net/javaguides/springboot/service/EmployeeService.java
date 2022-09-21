package net.javaguides.springboot.service;

import java.util.List;
import net.javaguides.springboot.model.Employee;

public interface EmployeeService {

	Employee saveEmployee( Employee employee);
	List<Employee> getAllEployees();
	Employee getEmployeeByID(long id);
	Employee updateEmployee(Employee employee, long id);
	void Delete (long id);
}
