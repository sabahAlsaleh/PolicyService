package se.kth.PolicyService.model.DTO;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;
    private String name;
    private String institution;
    private String position;
    private String rank;
    private String address;
    private LocalDate birthdate;

    private String fatherName;
    private String motherName;

    // Method to check age
    public int getAge() {
        return Period.between(this.birthdate, LocalDate.now()).getYears();
    }

    // Method to set parents' names if under 18
    public void setParentsNamesIfUnder18(String fatherName, String motherName) {
        if (getAge() < 18) {
            this.fatherName = fatherName;
            this.motherName = motherName;
        } else {
            this.fatherName = null;
            this.motherName = null;
        }
    }

}
