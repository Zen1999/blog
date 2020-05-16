## 社区



## 目的

学习 Spring Boot 和 Spring 家族，通过项目整合驱动学习，理解 Web 项目的开发方式



## 内容

- Spring Boot
- Spring MVC
- MyBatis
- MyBatis Plus
- Flyway
- Heroku
- Git/Github
- Maven
- Restful



## 现状

从零开始



## 开发环境

- JetBrains Intellij Idea 2020.1
- 



## 资料

[Spring 官方向导](https://spring.io/guides)

[Spring-web-content 文档](https://spring.io/guides/gs/serving-web-content/) 

[git 部署 ssh 密钥](https://developer.github.com/v3/guides/managing-deploy-keys/#deploy-keys) 

[bootstrap 搭建前端页面](https://v3.bootcss.com/getting-started/) 

[Github OAuth](https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/)

## 工具

[Git](https://git-scm.com/)

[Visual Paradigm](https://www.visual-paradigm.com/cn/)

[] ()

## 第三方库
[OkHttp](https://square.github.io/okhttp)

## 技巧

- 在使用 git 时，如果`git commit`后又想要修改内容，这时又不想重新编写提交信息，可以在`git add`之后输入`git commit --amend --no-edit`

- unable to start ssh-agent service, error :1058

- Set-Service -Name ssh-agent -StartupType automatic

  

## 第一天

### 创建 Spring Boot 项目

在 idea 中新建项目，选择 Spring Initializr，填上项目名包名，选择 web 模块，创建maven项目

### SpringBootApplication

SpringBootApplication.run(Class<?> primarySource, String... args) 是启动 Spring Boot 应用的入口函数，而 Spring Boot 是通过 Spring 的 ioc 容器来管理 Bean 的，只要在 SpringBootApplicaiton 同一级或者是下一级目录下，带注解的组件都会启动时被装载到 ioc 容器中进行管理

### application.propreties / application.yml

由于 Spring Boot 采用自动配置的理念，所以我们运行 Spring Boot 应用几乎不需要配置，创建后即可运行，如果我们想要自定义配置或者是修改自动配置的内容，就需要对 Spring Boot 进行配置了，application.propreties / application.yml 是 Spring Boot 的配置文件

#### 自定义常量

application.properties提供自定义属性的支持，这样我们就可以把一些常量配置在这里

```properties
zen.id = 1
zen.sex = "man"
```

如果需要使用到这些常量，则在需要绑定的字段前加上注解`@Value(value="${config.name}")`

```java
@Controller
public class ZenController {
    @Value("${zen.id}")
    private Integer id;
    @Value("${zen.sex}")
    private String sex;
}
```

当绑定的数据太多，我们就可以使用官方提倡绑定一个 bean 来注入数据

```java
@ConfigurationProperties(prefix = "zen")
public class ZenBean {
    private Integer id;
    private String name;
    // Ignore Getter & Setter
}
```

还要在 SpringBootApplication 的入口类加上 `@EnableConfigurationProperties(clazz)` 注解

```java
@SpringBootApplication
@EnableConfigurationProperties({ZenBean.class})
public class ZenApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZenApplication.class, args);
    }
}

```

最后在类中自动装载 bean 即可

```java
@Controller
public class ZenController {
    @Autowired
    ZenBean zenBean;
}
```

#### 参数间引用

在application.properties中的各个参数之间也可以直接引用来使用

```properties
zen.id = 1
zen.sex = "man"
zen.info = 编号:${zen.id}, 姓别:${zen.sex}
```



### Thymeleaf 模板引擎 与 Controller



### 创建 Github 仓库



### 创建 SSH 密钥



### Git Comment