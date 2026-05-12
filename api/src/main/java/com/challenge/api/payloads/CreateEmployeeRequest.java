package com.challenge.api.payloads;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmployeeRequest {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotNull
    @Min(0)
    private Integer salary;

    @NotNull
    @Min(18)
    @Max(65)
    private Integer age;

    @NotBlank
    private String jobTitle;

    @Email @NotBlank
    private String email;

    @NotNull
    @PastOrPresent
    private Instant contractHireDate;

    @Nullable
    @Future
    private Instant contractTerminationDate;

}