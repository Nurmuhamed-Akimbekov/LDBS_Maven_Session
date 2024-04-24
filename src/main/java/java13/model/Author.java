package java13.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class Author {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String country;
    private LocalDate dateOfBirth;


    public Author(String firstName, String lastName, String email, String country, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.country = country;
        this.dateOfBirth = dateOfBirth;

    }

    @Override
    public String toString() {
        return " \n Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", country='" + country + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }

}
