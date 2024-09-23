package kr.co.polycube.backendtest.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.polycube.backendtest.er.ErrorRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.regex.Pattern;

@Slf4j
@Component
public class UrlValidatorFilter implements Filter {

    private static final Pattern INVALID_CHARACTERS_PATTERN = Pattern.compile("[^a-zA-Z0-9/:?&=\\s]");

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        StringBuffer url = httpRequest.getRequestURL().append("?").append(httpRequest.getQueryString());

        if (INVALID_CHARACTERS_PATTERN.matcher(url).find()) {
            HttpServletResponse response = (HttpServletResponse) res;
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType("application/json");

            ErrorRes errorResponse = new ErrorRes("Invalid characters in URL");
            String jsonResponse = new ObjectMapper().writeValueAsString(errorResponse);
            response.getWriter().write(jsonResponse);
            return;
        }

        chain.doFilter(req, res);
    }
}
