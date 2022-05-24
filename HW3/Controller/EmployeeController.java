package com.demo.HW3.Controller;

import com.demo.HW3.Model.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {
    ArrayList<Employee> employees = new ArrayList<>();

    @GetMapping
    public ResponseEntity<ArrayList<Employee>> getEmployee() {
        return ResponseEntity.status(200).body(employees);
    }

    @PostMapping
    public ResponseEntity postEmployee(@RequestBody @Valid Employee employee, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        employees.add(employee);
        return ResponseEntity.status(201).body(employees);
    }

    //Update
    @PutMapping("/{index}")
    public ResponseEntity updateEmployee(@PathVariable Integer index, @RequestBody Employee employee) {
        employees.set(index, employee);
        return ResponseEntity.status(200).body(employee);
    }

    //Update
    @PutMapping("/AnnualLeave")
    public ResponseEntity checkAnnualLeave(@PathVariable String ID){
        Integer indx = getIndex(ID);
        if(indx == null){
            return ResponseEntity.status(400).body("there is no employee");
        } else if (employees.get(indx).getAnnualLeave() == 0){
            return ResponseEntity.status(400).body("bad request,There is no enough days for annual leave");
        } else if (employees.get(indx).isOnLeave() == true){
            employees.get(indx).setAnnualLeave((employees.get(indx).getAnnualLeave() - 1));
            return ResponseEntity.status(200).body("bad request, employee on annual leave");
        } else {
            return ResponseEntity.status(400).body("bad request, Employee has applied already to annual leave");
        }
    }

    public Integer getIndex(String ID){
        for (int i = 0; i < this.employees.size(); i++) {
            if (employees.get(i).getID().equals(ID)){
                return i;
            }
        }
        return null;
    }

    //Delete
    @DeleteMapping("/{index}")
    public ResponseEntity removeEmployee(@PathVariable Integer index) {
        employees.remove((int) index);
        return ResponseEntity.status(200).body(employees);
    }
}