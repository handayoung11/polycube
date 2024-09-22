package kr.co.polycube.backendtest.user.dto;

import lombok.Getter;

@Getter
public class SignUpResDTO {
    private final Long id;

    public SignUpResDTO(Long id) {
        this.id = id;
    }
}