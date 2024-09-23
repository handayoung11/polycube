package kr.co.polycube.backendtest.user;

import kr.co.polycube.backendtest.user.db.UserRepo;
import kr.co.polycube.backendtest.user.db.Users;
import kr.co.polycube.backendtest.user.dto.PatchUserReqDTO;
import kr.co.polycube.backendtest.user.dto.PatchUserResDTO;
import kr.co.polycube.backendtest.user.dto.SignUpReqDTO;
import kr.co.polycube.backendtest.user.dto.SignUpResDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserSvc {

    private final UserRepo userRepo;

    public SignUpResDTO signUp(SignUpReqDTO dto) {
        Users user = Users.createUser(dto);
        userRepo.save(user);
        return new SignUpResDTO(user.getId());
    }

    @Transactional
    public PatchUserResDTO updateUser(long id, PatchUserReqDTO u) {
        Users user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));
        user.update(u);
        return new PatchUserResDTO(user.getId(), user.getName());
    }
}
