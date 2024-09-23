package kr.co.polycube.backendtest.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PatchUserResDTO {
    private long id;
    private String name;
}
