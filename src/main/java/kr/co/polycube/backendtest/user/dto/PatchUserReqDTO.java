package kr.co.polycube.backendtest.user.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PatchUserReqDTO {
    @NotEmpty(message = "name is empty")
    private String name;
}