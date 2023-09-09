package com.techforb.Techforb.dto.request;

import com.techforb.Techforb.models.TypeDocumentEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NotBlank
    private String numberDocument;

    @NotNull
    private TypeDocumentEnum typeDocument;

    @NotBlank
    @Size(min = 6)
    private String password;
}