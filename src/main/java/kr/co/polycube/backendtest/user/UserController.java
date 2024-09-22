package kr.co.polycube.backendtest.user;

import kr.co.polycube.backendtest.user.dto.SignUpReqDTO;
import kr.co.polycube.backendtest.user.dto.SignUpResDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("users")
public class UserController {

    UserSvc userSvc;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SignUpResDTO createUser(@ModelAttribute SignUpReqDTO user) {
        return userSvc.signUp(user);
    }
}
