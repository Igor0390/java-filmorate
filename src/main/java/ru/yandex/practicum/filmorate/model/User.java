package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class User {

    @NotNull
    private int id;
    @NonNull
    @Email
    @NotBlank
    private String email;
    @NonNull
    @NotBlank
    private String login;
    @NonNull
    @NotBlank
    private String name;
    @NonNull
    @Past
    @NotNull
    private LocalDate birthday;

}
