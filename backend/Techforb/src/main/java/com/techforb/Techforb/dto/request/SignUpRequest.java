package com.techforb.Techforb.dto.request;

import com.techforb.Techforb.models.TypeDocumentEnum;
import jakarta.validation.constraints.Email;
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
public class SignUpRequest {

        @NotBlank
        @Size(min = 2, max = 60)
        private String fullname;

        @NotBlank
        @Email
        private String email;

        @NotNull
        private TypeDocumentEnum typeDocument;

        @NotBlank
        @Size(min = 6, max = 11)
        private String numberDocument;

        @NotBlank
        @Size(min = 6,max = 100)
        private String password;
}