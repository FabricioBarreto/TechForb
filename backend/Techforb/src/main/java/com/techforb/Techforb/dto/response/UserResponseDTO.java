package com.techforb.Techforb.dto.response;

import com.techforb.Techforb.models.TypeDocumentEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class UserResponseDTO {

    private String fullname;

    private String email;

    private TypeDocumentEnum typeDocument;

    private String numberDocument;

}
