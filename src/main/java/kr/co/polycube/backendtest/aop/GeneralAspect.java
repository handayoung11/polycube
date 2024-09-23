package kr.co.polycube.backendtest.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@AllArgsConstructor
public class GeneralAspect {

    private final HttpServletRequest request;

    @Before("execution(* kr.co.polycube.backendtest.user.UserController.createUser(..)) || " +
            "execution(* kr.co.polycube.backendtest.user.UserController.getUser(..)) || " +
            "execution(* kr.co.polycube.backendtest.user.UserController.updateUser(..))")
    public void logClientAgent() {
        String userAgent = request.getHeader("User-Agent");
        log.info("Client Agent: " + userAgent);
    }
}
