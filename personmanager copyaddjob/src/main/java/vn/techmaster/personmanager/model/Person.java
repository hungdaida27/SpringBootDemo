package vn.techmaster.personmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private int id;
    @NotBlank(message = "Name is required")
    @Size(min = 5, max = 30, message = "Name must between 5 and 30 characters")
    private String name;
    @Size(min = 2, max = 30, message = "Job must between 5 and 30 characters")
    private String job;
    private boolean gender;
    @NotBlank(message = "BirthDay is required")
    private String birthDay;
    private MultipartFile photo;
}
