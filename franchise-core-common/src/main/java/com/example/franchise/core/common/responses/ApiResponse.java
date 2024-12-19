package com.example.franchise.core.common.responses;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ApiResponse {
    private String code;
    private String debugMessage;
    private String message;
    private String status;
    private String timestamp;
}