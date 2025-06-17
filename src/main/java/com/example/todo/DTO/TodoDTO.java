package com.example.todo.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TodoDTO {
    private Integer id;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private String level;

    private LocalDateTime reg_date;

    private LocalDateTime last_update;
}
