package kr.co.polycube.backendtest.user.db;

import jakarta.persistence.*;
import kr.co.polycube.backendtest.user.dto.SignUpReqDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Users {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;

    public static Users createUser(SignUpReqDTO dto) {
        Users users = new Users();
        users.name = dto.getName();
        return users;
    }
}
