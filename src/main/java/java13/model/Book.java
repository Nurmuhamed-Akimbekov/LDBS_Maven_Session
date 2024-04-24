package java13.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Book {
    private Long id;
    private String name;
    private String country;
    private int publishedYear;
    private int price;
    private Long authorId;

    public Book(String name, String country, int publishedYear, int price, Long authorId) {
        this.name = name;
        this.country = country;
        this.publishedYear = publishedYear;
        this.price = price;
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return "\nBook{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", publishedYear=" + publishedYear +
                ", price=" + price +
                ", authorId=" + authorId +
                '}';
    }
}
