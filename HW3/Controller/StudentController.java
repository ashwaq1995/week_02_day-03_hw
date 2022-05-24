package com.demo.HW3.Controller;

import com.demo.HW3.Model.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {

    ArrayList<Student> students = new ArrayList<Student>();

    @GetMapping
    public ResponseEntity getStudent() {
        return ResponseEntity.status(200).body(students);
    }

    @PostMapping
    public ResponseEntity addStudent(@RequestBody @Valid Student student, Errors error){
        if(error.hasErrors()){
            return ResponseEntity.status(400).body(error.getFieldError().getDefaultMessage());
        }
        System.out.println(student);
        students.add(student);
        return ResponseEntity.status(200).body(student);
    }

    //Update
    @PutMapping("/{index}")
    public ResponseEntity editStudent(@PathVariable Integer index, @RequestBody Student student) {
        students.set(index, student);
        return ResponseEntity.status(200).body(student);
    }

    @DeleteMapping("/{index}")
    public ResponseEntity removeStudent(@PathVariable Integer index) {
        students.remove((int) index);
        return ResponseEntity.status(200).body(students);
    }
}
