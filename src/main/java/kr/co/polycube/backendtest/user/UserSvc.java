package kr.co.polycube.backendtest.user;

import kr.co.polycube.backendtest.user.db.UserRepo;
import kr.co.polycube.backendtest.user.db.Users;
import kr.co.polycube.backendtest.user.dto.SignUpReqDTO;
import kr.co.polycube.backendtest.user.dto.SignUpResDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserSvc {

    private final UserRepo userRepo;

    public SignUpResDTO signUp(SignUpReqDTO dto) {
        Users user = Users.createUser(dto);
        userRepo.save(user);
        return new SignUpResDTO(user.getId());
    }
}
