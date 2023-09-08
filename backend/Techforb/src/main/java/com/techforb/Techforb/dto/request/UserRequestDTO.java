package com.Techforb.Techforb.dto.request;

import com.Techforb.Techforb.models.TypeDocumentEnum;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class UserRequestDTO {


    @NotBlank
    @Size(min = 2, max = 100)
    private String fullname;

    @NotBlank
    @Email
    private String email;

    @NotNull
    private TypeDocumentEnum typeDocument;

    @NotBlank
    @Pattern(regexp = "\\d{8}")
    private String numberDocument;

    private String imageUrl;

    @NotBlank
    @Size(min = 6, max = 100)
    private String password;

}
