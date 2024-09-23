package kr.co.polycube.backendtest.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetUserResDTO {
    private long id;
    private String name;
}
