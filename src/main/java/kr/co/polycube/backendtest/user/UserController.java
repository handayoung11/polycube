package kr.co.polycube.backendtest.user;

import jakarta.validation.Valid;
import kr.co.polycube.backendtest.user.db.UserRepo;
import kr.co.polycube.backendtest.user.db.Users;
import kr.co.polycube.backendtest.user.dto.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("users")
public class UserController {

    UserSvc userSvc;
    UserRepo userRepo;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SignUpResDTO createUser(@ModelAttribute @Valid SignUpReqDTO user) {
        return userSvc.signUp(user);
    }

    @GetMapping("{id}")
    public GetUserResDTO getUser(@PathVariable long id) {
        Users user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));
        return new GetUserResDTO(user.getId(), user.getName());
    }

    @PatchMapping("{id}")
    public PatchUserResDTO updateUser(@PathVariable long id,
                                     @ModelAttribute
                                     @Valid PatchUserReqDTO u) {
        return userSvc.updateUser(id, u);
    }
}
