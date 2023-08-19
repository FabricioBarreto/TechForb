package com.techforb.Techforb.dto.request;

import com.techforb.Techforb.models.TypeDocumentEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
        String fullname;
        String email;
        TypeDocumentEnum typeDocument;
        String numberDocument;
        String password;
}