package com.example.bloglab12.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "title should not be empty")
    @Size(min = 3, max = 50, message = "title should range between 3 and 50 characters")
    @Column(columnDefinition = "VARCHAR(50) NOT NULL")
    private String title;
    @NotEmpty(message = "body should not be empty")
    @Size(min = 5, max = 200, message = "body should range between 5 and 200 characters")
    @Column(columnDefinition = "VARCHAR(200) NOT NULL")
    private String body;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;
}
