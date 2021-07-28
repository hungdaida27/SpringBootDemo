package vn.techmaster.personmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Job {
    private int id;
    @NotBlank(message = "Job of name is required!")
    private String name;
}
