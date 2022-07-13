package com.example.HealFitNest.Model;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "RegisteredUsers")

public class Users {
    @Id
    private String userId;
    private String userName;
    private String userEmail;
    private String userPassword;
    private long userNumber;

}
