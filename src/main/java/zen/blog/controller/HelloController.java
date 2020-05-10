package zen.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Zen
 * @version 1.0
 * @date 2020/5/10 18:14
 */

@Controller
public class HelloController {

  @GetMapping("/hello")
  public String hello(@RequestParam(name = "name") String name, Model model) {
    // Model 本质就是 Map，它能将请求参数传递到模板引擎中
    model.addAttribute("name", name);
    // Spring Boot 会在 resource 文件夹中查找 返回字符串名的模板，并跳转
    return "hello";
  }
}
