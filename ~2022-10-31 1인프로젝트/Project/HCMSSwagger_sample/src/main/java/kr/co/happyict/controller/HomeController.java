package kr.co.happyict.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @GetMapping(value= {"/", "/v2/api-docs"})
  public void index(HttpServletResponse response) throws IOException {
    response.sendRedirect("/swagger-ui/index.html");
  }
}
