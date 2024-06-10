package se.kth.PolicyService.model.Attributes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String id;
  //  private String Dob;
    private String name;
    private String position;
    private String department;
}
