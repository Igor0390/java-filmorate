package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class User {
    @Positive
    @NotNull
    private int id;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String login;
    @NotBlank
    private String name;
    @Past
    @NotNull
    private LocalDate birthday;

}
