package com.jayoswal.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(
        name = "Response",
        description = "Schema to hold success response only"
)
public class ResponseDto {

    @Schema(
            description = "Status code of successful response", example = "200"
    )
    private String statusCode;

    @Schema(
            description = "Status message of successful response", example = "Request is processed successfully"
    )
    private String statusMsg;
}
