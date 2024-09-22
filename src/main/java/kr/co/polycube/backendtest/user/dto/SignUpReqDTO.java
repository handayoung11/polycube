package kr.co.polycube.backendtest.user.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpReqDTO {
    @NotEmpty(message = "name is empty")
    private String name;
}