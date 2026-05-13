package com.challenge.api.payloads;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GlobalErrorResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private String httpStatus;
}
