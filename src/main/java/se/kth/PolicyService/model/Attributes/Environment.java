package se.kth.PolicyService.model.Attributes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Environment {
    private LocalTime time;
   // private Date date;
    private String location;


}
