package se.kth.PolicyService.model.Attributes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    private String name;
    private List<String> permissions;


}
