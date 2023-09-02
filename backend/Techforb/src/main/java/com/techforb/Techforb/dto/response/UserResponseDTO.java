package com.techforb.Techforb.dto.response;

import com.techforb.Techforb.config.amazon3.service.IAWSClientService;
import com.techforb.Techforb.models.TypeDocumentEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.multipart.FilePart;

import java.net.URL;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class UserResponseDTO {

    private String fullname;

    private String email;

    private TypeDocumentEnum typeDocument;

    private URL imageUrl;

    private String numberDocument;

}
