package com.techforb.Techforb.dto.request;

import com.techforb.Techforb.models.TypeDocumentEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class UserRequestDTO {

    private String fullname;

    private String email;

    private TypeDocumentEnum typeDocument;

    private String numberDocument;

    private String password;

}
