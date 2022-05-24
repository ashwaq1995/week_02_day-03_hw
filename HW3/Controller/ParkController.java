package com.demo.HW3.Controller;

import com.demo.HW3.Model.Park;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/ride")
public class ParkController {

    ArrayList<Park> parks =new ArrayList<Park>();

    //Get all the rides
    @GetMapping
    public ResponseEntity getPark() {
        return ResponseEntity.status(200).body(parks);
    }

    //Add new ride
    @PostMapping
    public ResponseEntity postPark(@RequestBody @Valid Park park, Errors error){
        if (error.hasErrors()){
            return ResponseEntity.status(400).body(error.getFieldError().getDefaultMessage());
        }
        parks.add(park);
        return ResponseEntity.status(201).body(parks);
    }

    //Update ride
    @PutMapping("/{index}")
    public ResponseEntity updatePark(@PathVariable Integer index, @RequestBody Park park) {
        parks.set(index, park);
        return ResponseEntity.status(200).body(park);
    }

    @PutMapping("sell/{ID}")
    public ResponseEntity sellParks(@PathVariable String ID , @RequestBody int amount){
        Integer index = getIndex(ID);
        if (index == null){
            return ResponseEntity.status(400).body("There is no ride");
        } else if (amount >= parks.get(index).getPrice()){
            return ResponseEntity.status(400).body("Not enough amount");
        } else if(parks.get(index).getTickets() <= 0){
            return ResponseEntity.status(400).body("Sorry! Tickets are sold out");
        }
        parks.get(index).setTickets(parks.get(index).getTickets()-1);
        return ResponseEntity.status(200).body("One ticket has been sold");
    }
    public Integer getIndex(String id){
        for (int i = 0; i < this.parks.size(); i++) {
            if (parks.get(i).getID().equals(id)){
                return i;
            }
        }
        return null;
    }

    //Delete ride
    @DeleteMapping("/{index}")
    public ResponseEntity removePark(@PathVariable Integer index) {
        parks.remove((int) index);
        return ResponseEntity.status(200).body(parks);
    }
}
