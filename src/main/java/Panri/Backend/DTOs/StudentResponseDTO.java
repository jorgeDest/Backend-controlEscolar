package Panri.Backend.DTOs;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonPropertyOrder({ "firstName", "lastName", "enrollmentCode", "grade", "groupName" })
@Data
public class StudentResponseDTO {
    private String firstName;
    private String lastName;
    private String enrollmentCode;
    private String grade;
    private String groupName;
}
