package com.jayoswal.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(
        name = "Error Response",
        description = "Schema to hold success response only"
)
public class ErrorResponseDto {

    @Schema(
            description = "api path", example = "200"
    )
    private String apiPath;

    @Schema(
            description = "Error code of response", example = "400"
    )
    private HttpStatus errorCode;

    @Schema(
            description = "Error message of response", example = "something failed"
    )
    private String errorMsg;

    @Schema(
            description = "Timestamp", example = "2024-08-04T16:25:40.842148"
    )
    private LocalDateTime errorTime;
}
