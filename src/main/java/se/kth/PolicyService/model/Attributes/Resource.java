package se.kth.PolicyService.model.Attributes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resource {
    private String id;
    private String type;
    private String owner;
    private String creationDate;
}
