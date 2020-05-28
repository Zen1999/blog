package zen.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zen.community.Mapper.QuestionMapper;
import zen.community.model.Question;
import zen.community.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author Zen
 * @version 1.0
 * @date 2020/5/25 14:50
 */
@Controller
public class PublishController {

  @Autowired
  QuestionMapper questionMapper;

  @GetMapping("/publish")
  public String publish() {
    return "publish";
  }

  @PostMapping("/publish")
  public String doPublish(@RequestParam("title") String title,
                          @RequestParam("description") String description,
                          @RequestParam("tags") String tags,
                          HttpServletRequest request,
                          Model model) {
    User user = (User)request.getSession().getAttribute("user");
    if (!Objects.isNull(user)) {
      if (title.equals("")) {
        model.addAttribute("error", "标题不能为空");
        return "publish";
      }
      if (description.equals("")) {
        model.addAttribute("error", "问题描述不能为空");
        return "publish";
      }
      if (tags.equals("")) {
        model.addAttribute("error", "标签不能为空");
        return "publish";
      }
      Question question = new Question(title, description, System.currentTimeMillis(), user.getId(), tags);
      question.setGmtModified(question.getGmtCreate());
      questionMapper.create(question);
    } else {
      model.addAttribute("error", "用户未登录");
      model.addAttribute("title", title);
      model.addAttribute("description", description);
      model.addAttribute("tags", tags);
      return "publish";
    }
    return "redirect:/";
  }
}
