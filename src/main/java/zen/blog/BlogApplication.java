package zen.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlogApplication {

  public static void main(String[] args) {
    // Spring 通过 ioc容器 来管理 Bean，
    // 只要在 SpringBootApplication 同一级或下一级目录下 带注解的组件都会加载进来-
    SpringApplication.run(BlogApplication.class, args);
  }

}
