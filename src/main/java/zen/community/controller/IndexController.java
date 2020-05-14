package zen.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Zen
 * @version 1.0
 * @date 2020/5/13 20:32
 */
@Controller
public class IndexController {

  @GetMapping("/index")
  public String index() {
    return "index";
  }
}
