package com.example.EmployeeManagementSystem.controller;

import com.example.EmployeeManagementSystem.model.ActiveEmployee;
import com.example.EmployeeManagementSystem.model.Employee;
import com.example.EmployeeManagementSystem.repository.ActiveEmployeeRepository;
import com.example.EmployeeManagementSystem.repository.EmployeeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeRepository employeeRepository;
    private final ActiveEmployeeRepository activeEmployeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository, ActiveEmployeeRepository activeEmployeeRepository) {
        this.employeeRepository = employeeRepository;
        this.activeEmployeeRepository = activeEmployeeRepository;
    }

    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }
    @PostMapping("/addEmployee")
    public Employee addEmployee(@RequestBody Employee employee){
        return employeeRepository.save(employee);
    }

    @PostMapping("/addActiveEmployee")
    public Employee addActiveEmployee(@RequestBody ActiveEmployee activeEmployee){
        return employeeRepository.save(activeEmployee);
    }
    @GetMapping("/activeEmployees")
    public List<ActiveEmployee> getActiveEmployees(){
        return activeEmployeeRepository.findAll();
    }
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee existingEmployee = optionalEmployee.get();
            existingEmployee.setName(employee.getName());
            Employee updatedEmployee = employeeRepository.save(existingEmployee);
            return ResponseEntity.ok(updatedEmployee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/activeEmployees/{id}")
    public ResponseEntity<ActiveEmployee> updateActiveEmployee(@PathVariable Long id, @RequestBody ActiveEmployee activeEmployee) {
        Optional<ActiveEmployee> optionalActiveEmployee = activeEmployeeRepository.findById(id);
        if (optionalActiveEmployee.isPresent()) {
            ActiveEmployee existingActiveEmployee = optionalActiveEmployee.get();
            existingActiveEmployee.setName(activeEmployee.getName());
            existingActiveEmployee.setSalary(activeEmployee.getSalary());
            existingActiveEmployee.setDateOfEmployment(activeEmployee.getDateOfEmployment());
            ActiveEmployee updatedActiveEmployee = activeEmployeeRepository.save(existingActiveEmployee);
            return ResponseEntity.ok(updatedActiveEmployee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("employees/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return ResponseEntity.ok("Employee with id " + id + " has been successfully deleted.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("activeEmployees/{id}")
    public ResponseEntity<String> deleteActiveEmployee(@PathVariable Long id) {
        if (activeEmployeeRepository.existsById(id)) {
            activeEmployeeRepository.deleteById(id);
            return ResponseEntity.ok("Active employee with id " + id + " has been successfully deleted.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}