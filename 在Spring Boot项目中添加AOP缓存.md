# 在Spring Boot项目中添加AOP缓存

> 在Spring Boot项目中添加AOP缓存，你可以使用Spring的内置缓存支持，或者使用其他的缓存库，如EhCache，Redis等。以下是一个基本的步骤，说明如何使用Spring的内置缓存支持来添加AOP缓存。

1. **添加依赖**

   在你的`pom.xml`文件中，添加Spring Boot的缓存启动器依赖：

   ```xml
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-cache</artifactId>
   </dependency>
   ```

2. **启用缓存**

   在你的Spring Boot主配置类上添加`@EnableCaching`注解，以启用缓存：

   ```java
   @SpringBootApplication
   @EnableCaching
   public class Application {
       public static void main(String[] args) {
           SpringApplication.run(Application.class, args);
       }
   }
   ```

3. **添加缓存注解**

   &emsp;在你想要缓存的方法上添加`@Cacheable`注解。例如，如果你有一个方法用于获取用户信息，你可以这样做：

   ```java
   @Service
   public class UserService {
       @Cacheable(value = "users", key = "#userId")
       public User getUser(String userId) {
           // your code to get user
       }
   }
   ```

   &emsp;在这个例子中，`value`参数定义了缓存的名称，`key`参数定义了缓存的键。每次调用`getUser`方法时，Spring都会检查是否已经有一个名为`users`的缓存，并且键为`userId`的值。如果有，Spring将返回缓存的值，而不是调用方法。如果没有，Spring将调用方法，然后将结果存储在缓存中。

4. **清除缓存**

   &emsp;你可以使用`@CacheEvict`注解来清除缓存。例如，如果你有一个方法用于更新用户信息，你可能希望在更新用户信息后清除用户的缓存：

   ```java
   @Service
   public class UserService {
       @CacheEvict(value = "users", key = "#user.id")
       public void updateUser(User user) {
           // your code to update user
       }
   }
   ```

   在这个例子中，每次调用`updateUser`方法时，Spring都会清除名为`users`的缓存，并且键为`user.id`的值。

&emsp;以上就是在Spring Boot项目中添加AOP缓存的基本步骤。这只是一个基本的例子，实际的使用可能会更复杂。例如，你可能需要配置你的缓存策略，或者使用不同的缓存库。你可以查阅Spring的官方文档，了解更多关于Spring缓存的信息。

## 一、配置缓存策略

&emsp;Spring Boot提供了一种简单的方式来配置缓存策略，你可以在`application.properties`或`application.yml`文件中进行配置。

以下是一些常见的缓存配置属性：

1. **spring.cache.type**：用于设置缓存类型，例如：`NONE`, `SIMPLE`, `JDK`, `GUAVA`, `CAFFEINE`, `REDIS`, `HAZELCAST`, `INFINISPAN`, `COUCHBASE`, `GENODE`, `NONE`等。
2. **spring.cache.cache-names**：用于在启动时创建的缓存名称，逗号分隔。
3. **spring.cache.caffeine.spec**：用于Caffeine缓存库的配置字符串，例如：`maximumSize=500,expireAfterAccess=600s`。

以下是一个`application.properties`文件的例子，它配置了Caffeine缓存库：

```properties
spring.cache.type=caffeine
spring.cache.cache-names=users
spring.cache.caffeine.spec=maximumSize=500,expireAfterAccess=600s
```

需要添加Caffeine库到项目中

```xml
<dependency>
    <groupId>com.github.ben-manes.caffeine</groupId>
    <artifactId>caffeine</artifactId>
</dependency>
```

&emsp;在这个例子中，我们设置了缓存类型为Caffeine，创建了一个名为`users`的缓存，并设置了Caffeine的配置字符串，指定了最大缓存数量为500，访问后的过期时间为600秒。

&emsp;这只是一个基本的例子，实际的配置可能会更复杂。你可以查阅Spring Boot的官方文档，了解更多关于缓存配置的信息。

## 二、在本项目中的使用示例

![image-20230625161432104](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20230625161432104.png)

&emsp;对以上两个方法进行缓存，使得同一用户多次登陆不需要重复去数据库中查找用户信息和权限信息。

![image-20230625161559693](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20230625161559693.png)

&emsp;其中，配置文件需要做出如下配置

![image-20230625161641461](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20230625161641461.png)

[存在问题](#AOP缓存问题)

# AOP可以用来做什么

&emsp;面向切面编程（Aspect-Oriented Programming，AOP）是一种编程范式，其目标是提高模块化程度，特别是对于横切关注点（cross-cutting concerns）。横切关注点是那些影响应用的多个部分的问题，例如日志记录、事务管理、安全等。通过使用AOP，你可以将这些横切关注点从它们所影响的业务逻辑中分离出来，从而提高代码的模块化程度。

以下是一些使用AOP的常见用例：

1. **日志记录**：你可以使用AOP来记录方法的执行时间，这对于性能调优非常有用。例如：

   ```java
   @Aspect
   @Component
   public class LoggingAspect {
   
       @Around("execution(* com.example.myapp.*.*(..))")
       public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
           long start = System.currentTimeMillis();
   
           Object proceed = joinPoint.proceed();
   
           long executionTime = System.currentTimeMillis() - start;
   
           System.out.println(joinPoint.getSignature() + " executed in " + executionTime + "ms");
           return proceed;
       }
   }
   ```

   在这个例子中，`@Around`注解定义了一个环绕通知，它会在目标方法执行前后运行。`execution(* com.example.myapp.*.*(..))`是一个切点表达式，它匹配`com.example.myapp`包下的所有方法。这个通知记录了方法的执行时间，并将其打印到控制台。[详细](#13)

2. **事务管理**：你可以使用AOP来管理数据库事务。例如，你可以定义一个通知，它在执行数据库操作前开始一个事务，在操作成功后提交事务，或在发生异常时回滚事务。Spring框架提供了`@Transactional`注解，它就是使用AOP实现的。

3. **安全**：你可以使用AOP来实现安全控制，例如，检查用户是否有执行某个操作的权限。例如：

   ```java
   @Aspect
   @Component
   public class SecurityAspect {
   
       @Before("execution(* com.example.myapp.*.*(..)) && @annotation(RequiresAdmin)")
       public void checkAdmin(JoinPoint joinPoint) {
           // check if the user is an admin
           // if not, throw an exception
       }
   }
   ```

   在这个例子中，`@Before`注解定义了一个前置通知，它会在目标方法执行前运行。这个通知检查用户是否是管理员，如果不是，就抛出一个异常。`@annotation(RequiresAdmin)`是一个切点表达式，它匹配所有被`@RequiresAdmin`注解的方法。也可以使用切点表达式：`execution(* com.jinzunyue.share..*.*(..))`，其中`..`表示这个包及其所有子包，这样可以匹配share下所有的类方法以及子包里的类方法。

以上就是一些使用AOP的常见用例。需要注意的是，虽然AOP是一个强大的工具，但它也应该谨慎使用。过度使用AOP可能会导致代码难以理解和维护。

## 一、日志记录

&emsp;使用Spring AOP进行<a name="13">日志记录</a>，你需要导入Spring AOP和AspectJ的相关依赖。如果你的项目是基于Spring Boot的，你可以在你的`pom.xml`文件中添加以下依赖：

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
</dependency>
```

`spring-boot-starter-aop`是一个Spring Boot启动器，它包含了Spring AOP和AspectJ的依赖，所以你不需要单独添加这些依赖。

&emsp;此外，你还需要在你的项目中启用AspectJ的支持。你可以在你的配置类上添加`@EnableAspectJAutoProxy`注解来实现这一点：

```java
@Configuration
@EnableAspectJAutoProxy
public class AppConfig {
    // ...
}
```

&emsp;这样，你就可以在你的项目中使用AspectJ的注解，如`@Aspect`、`@Before`、`@After`、`@Around`等，来定义切面和通知了。

> 注意：以上AppConfig在本项目中可以不写，代码仍能正常跑。

# AOP缓存问题

## 一、使用AOP缓存并没有对程序效率产生太大影响

### 1、查看项目使用的数据库系统和ORM框架

查看项目使用的<font color="purple">数据库系统</font>和<font color="purple">ORM框架</font>通常可以通过以下几种方式：

1. **查看项目的依赖**：在你的项目的构建文件（如Maven的`pom.xml`或Gradle的`build.gradle`）中，你可以看到项目的所有依赖。如果你看到了如`mysql-connector-java`、`postgresql`、`h2`等依赖，那么你的项目可能使用的是MySQL、PostgreSQL、H2等数据库。如果你看到了如`spring-boot-starter-data-jpa`、`spring-boot-starter-data-jdbc`、`mybatis-spring-boot-starter`等依赖，那么你的项目可能使用的是JPA、JDBC、MyBatis等ORM框架。
2. **查看项目的配置**：在你的项目的配置文件（如`application.properties`或`application.yml`）中，你可以看到数据库和ORM框架的配置。例如，`spring.datasource.url`属性通常包含了数据库的类型和位置，`spring.jpa`或`mybatis`属性通常包含了ORM框架的配置。
3. **查看项目的代码**：在你的项目的代码中，你可以看到数据库和ORM框架的使用。例如，如果你看到了`@Entity`、`@Table`、`@Column`等注解，那么你的项目可能使用的是JPA。如果你看到了`@Mapper`、`@Select`、`@Insert`等注解，那么你的项目可能使用的是MyBatis。

&emsp;以上是一些常见的查看项目使用的数据库系统和ORM框架的方法。需要注意的是，具体的方法可能会根据你的项目的具体情况而变化。

&emsp;通过上面的步骤，可以确定本项目使用的是MyBatis和JPA。

&emsp;MyBatis和JPA都有自己的缓存机制，这可能是你在启用Spring Boot缓存前后没有看到明显区别的一个原因。

1. **MyBatis**：MyBatis有一个内置的一级缓存，它默认开启。一级缓存是基于每个SQL会话的，当会话结束时，缓存就会被清空。这意味着在同一个会话中，如果执行了相同的SQL查询，第一次查询的结果会被缓存，后续的查询会直接从缓存中获取结果，而不需要再次查询数据库。MyBatis也支持二级缓存，它是跨会话的，但需要手动配置。[SQL会话是什么？](#1)
2. **JPA**：JPA（特别是其实现如Hibernate）也有一级和二级缓存。一级缓存是基于每个持久化上下文的，当上下文结束时，缓存就会被清空。二级缓存是跨上下文的，它需要手动配置，并且需要支持二级缓存的JPA实现（如Hibernate）。

&emsp;这些ORM框架的<font color="purple">缓存机制</font>可能会影响你在启用Spring Boot缓存前后看到的性能差异。如果你的查询结果已经被ORM框架缓存，那么即使你没有启用Spring Boot缓存，你也可能看到相似的性能。

&emsp;然而，需要注意的是，ORM框架的缓存通常只对单个应用实例有效。如果你的应用有多个实例，或者你需要缓存跨请求或跨会话的数据，你可能还需要使用Spring Boot缓存或其他缓存解决方案。

&emsp;如果你想要看到Spring Boot缓存的效果，你可能需要禁用ORM框架的缓存，或者使用一个不被ORM框架缓存的查询。你也可以使用一个缓存统计工具或库，如Caffeine的`stats()`方法，来查看Spring Boot缓存的统计信息。

**<font color="blue"><a name="1">SQL会话是什么？</a></font>**

&emsp;在MyBatis中，一个"SQL会话"是指<font color="green">从打开数据库连接到关闭连接的过程</font>。在这个过程中，你可以执行多个SQL操作，如查询、插入、更新和删除。这个过程也被称为<font color="green">一个数据库事务</font>。

&emsp;当你使用MyBatis的`SqlSession`对象执行SQL操作时，你实际上是在一个SQL会话中。例如：

```java
try (SqlSession session = sqlSessionFactory.openSession()) {
    UserMapper mapper = session.getMapper(UserMapper.class);
    User user = mapper.selectUser(1);
    // ...
}
```

&emsp;在这个例子中，`sqlSessionFactory.openSession()`打开了一个新的SQL会话，`session.getMapper(UserMapper.class)`获取了一个映射器，然后使用这个映射器执行了一个查询。当`try`块结束时，会话会自动关闭，这是因为`SqlSession`实现了`AutoCloseable`接口。

&emsp;MyBatis的一级缓存是<font color="green">基于每个SQL会话的</font>。这意味着在同一个会话中，如果你执行了相同的查询，第一次查询的结果会被缓存，后续的查询会直接从缓存中获取结果，而不需要再次查询数据库。然而，当会话结束（即数据库连接关闭）时，缓存就会被清空。

&emsp;这种缓存策略可以提高性能，因为它避免了在同一个会话中多次执行相同的查询。然而，它只对单个会话有效，如果你在一个新的会话中执行相同的查询，你还是需要查询数据库。

&emsp;本项目的登录方法需要调用下面两个service层方法：

![image-20230625175944423](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20230625175944423.png)

&emsp;如果你想要确认这两个方法调用是否使用同一个SqlSession，[你可以在这两个方法中添加一些日志语句，打印出SqlSession的ID或hashCode](#3)。如果这两个值相同，那么这两个方法调用就是使用同一个SqlSession。

**<font color="blue"><a name="3">在方法中添加一些日志语句，打印出SqlSession的ID或hashCode：</a></font>**

&emsp;在MyBatis中，`SqlSession`对象没有提供获取ID的方法，但你可以通过打印`SqlSession`对象的`hashCode()`来获取一个可以用于区分不同`SqlSession`对象的值。以下是一个示例：

```java
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class YourServiceClass {

    private static final Logger logger = LoggerFactory.getLogger(YourServiceClass.class);

    @Autowired
    private SqlSession sqlSession;

    public User findByUsername(String username) {
        logger.info("SqlSession hashCode in findByUsername: " + sqlSession.hashCode());
        return userMapper.findByUsername(username);
    }

    @Cacheable(value = "role",key = "#userId")
    public List<Role> findRolesByUserId(Integer userId) {
        logger.info("SqlSession hashCode in findRolesByUserId: " + sqlSession.hashCode());
        return userMapper.findRolesByUserId(userId);
    }
}
```

&emsp;在这个示例中，我们使用了SLF4J库来记录日志。你可以根据你的项目的具体情况，使用SLF4J、Log4j、Java Util Logging或其他日志库。

&emsp;你需要导入SLF4J（Simple Logging Facade for Java）的依赖。SLF4J是一个为各种logging API（如java.util.logging, logback, log4j等）提供了一个简单统一的接口，使得最终用户可以在部署的时候选择所希望的logging API实现。

&emsp;如果你的项目使用Maven作为构建工具，你可以在你的`pom.xml`文件中添加以下依赖：

```xml
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <version>1.7.30</version>
</dependency>
```

&emsp;同时，你还需要一个SLF4J的实现，如Logback或Log4j。例如，如果你选择使用Logback，你可以添加以下依赖：

```xml
<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-classic</artifactId>
    <version>1.2.3</version>
</dependency>
```

&emsp;需要注意的是，这个示例假设你的`SqlSession`是由Spring管理的，即你在你的服务类中自动装配了`SqlSession`。如果你的`SqlSession`不是由Spring管理，或者你在每个方法调用前后都手动打开和关闭`SqlSession`，那么你可能需要在你打开和关闭`SqlSession`的地方添加日志语句。

&emsp;在Spring和MyBatis的集成中，同一个事务（在这种情况下，同一个HTTP请求）中的所有数据库操作都会使用同一个`SqlSession`。

&emsp;然而，你的日志也显示了在不同的HTTP请求中，`SqlSession`的hashCode仍然相同。这可能是因为你的`SqlSession`对象是由Spring管理的，并且被配置为单例。在这种情况下，[尽管`SqlSessionTemplate`会为每个数据库操作创建一个新的`SqlSession`，但是你在代码中注入的`SqlSession`对象始终是同一个。](#template)

&emsp;在Spring中，一个事务通常对应一个服务方法。当你在一个服务方法上添加`@Transactional`注解时，Spring会在这个方法开始时开启一个新的事务，然后在这个方法结束时提交或回滚这个事务。在这个方法中，所有的Mapper方法调用都会使用同一个`SqlSession`。

&emsp;例如，假设你有以下的服务方法：

```java
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public void updateUser(User user) {
        userMapper.updateUser(user);
        userMapper.updateUserRoles(user);
    }
}
```

&emsp;在这个例子中，`updateUser`方法是一个事务。当这个方法被调用时，Spring会开启一个新的事务，并且`userMapper.updateUser(user)`和`userMapper.updateUserRoles(user)`会使用同一个`SqlSession`。当这个方法结束时，事务会被提交，`SqlSession`会被关闭。

&emsp;所以，每个Mapper方法不一定会使用一个新的`SqlSession`，它们会在同一个事务中使用同一个`SqlSession`。

**<a name="template">尽管`SqlSessionTemplate`会为每个数据库操作创建一个新的`SqlSession`，但是你在代码中注入的`SqlSession`对象始终是同一个。</a>**

&emsp;在Spring和MyBatis的集成中，`SqlSessionTemplate`是`SqlSession`的一个实现，它负责管理`SqlSession`的生命周期。当你在你的代码中执行一个数据库操作（例如，调用一个Mapper方法）时，`SqlSessionTemplate`会创建一个新的`SqlSession`来执行这个操作，然后在操作结束后关闭这个`SqlSession`。

&emsp;然而，`SqlSessionTemplate`本身是一个Spring管理的bean，它的生命周期由Spring来管理。在默认情况下，Spring会为每个bean创建一个实例，并在整个应用中复用这个实例。这意味着在你的整个应用中，`SqlSessionTemplate`只有一个实例。

&emsp;当你在你的代码中使用`@Autowired`注解来注入`SqlSession`时，你实际上是在注入`SqlSessionTemplate`的一个实例。因为`SqlSessionTemplate`是一个单例，所以无论你在哪里注入`SqlSession`，你都会得到同一个`SqlSessionTemplate`实例。

&emsp;这就是我说的"你在代码中注入的`SqlSession`对象始终是同一个"的意思。尽管`SqlSessionTemplate`会为每个数据库操作创建一个新的`SqlSession`，但是你在代码中注入的`SqlSession`（实际上是`SqlSessionTemplate`）始终是同一个。

&emsp;在Spring和MyBatis的集成中，`SqlSessionTemplate`通常被配置为单例，这是因为`SqlSessionTemplate`是线程安全的，并且<font color="green">被设计为在多个数据库操作中复用</font>。`SqlSessionTemplate`会为每个数据库操作创建一个新的`SqlSession`，并管理这个`SqlSession`的生命周期。这意味着即使`SqlSessionTemplate`是一个单例，每个数据库操作仍然会使用一个新的`SqlSession`。

&emsp;如果你想要观察数据库会话的创建情况，你不需要改变`SqlSessionTemplate`的配置。相反，你可以[使用MyBatis的日志功能或者一些第三方工具来监控你的数据库操作](#12)。例如，你可以在你的MyBatis配置文件中启用日志，然后在你的日志配置文件中设置适当的日志级别。这样，你就可以在你的日志中看到每个数据库操作的详细信息，包括执行的SQL语句，使用的参数，返回的结果等。

&emsp;此外，你也可以使用一些数据库管理工具来查看你的数据库服务器的状态，或者使用性能分析工具来查看你的应用的数据库操作性能。

&emsp;请注意，这些方法可能需要一些额外的配置和工作，你可能需要根据你的具体需求和环境来选择适合你的方法。

### 2、通过打印日志以及观察SqlSession来判断问题所在

**<a name="12">使用MyBatis的日志功能或者一些第三方工具来监控你的数据库操作</a>**

如果你想要观察数据库会话的创建情况，你可以通过配置MyBatis的日志来实现。MyBatis支持多种日志实现（如SLF4J，Log4J，Log4J2，JDK logging等），你可以选择其中一种来使用。

以下是如何配置MyBatis使用SLF4J进行日志记录的步骤：

1. 首先，你需要在你的项目中添加SLF4J和它的实现（如Logback）的依赖。如果你的项目使用Maven，你可以在你的`pom.xml`文件中添加以下依赖：

```xml
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <version>1.7.30</version>
</dependency>
<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-classic</artifactId>
    <version>1.2.3</version>
</dependency>
```

​	2.然后，你需要在你的MyBatis配置文件中指定使用SLF4J进行日志记录。你可以在你的MyBatis配置文件（通常是`mybatis-config.xml`）中添加以下配置：

```xml
<configuration>
    <settings>
        <setting name="logImpl" value="SLF4J"/>
    </settings>
</configuration>
```

​	3.最后，你需要配置SLF4J（和它的实现）来记录你想要的日志。例如，如果你使用Logback，你可以在你的`logback.xml`文件中添加以下配置，来记录所有的SQL语句和结果：

```xml
<configuration>
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>

    <logger name="org.mybatis" level="DEBUG"/>
    <logger name="java.sql" level="DEBUG"/>

    <root level="info">
        <appender-ref ref="STDOUT" />
    </root>
</configuration>
```

&emsp;这样，你就可以在你的控制台看到所有的SQL语句和结果了。你可以根据你的需要调整日志级别和格式。

&emsp;请注意，这些步骤可能需要根据你的具体环境和需求进行调整。你应该查阅MyBatis，SLF4J和Logback的官方文档，以获取更详细和准确的信息。

**<font color="blue">详细步骤</font>**

&emsp;如果你的项目没有MyBatis配置文件和SLF4J的配置文件，你可能需要创建它们。下面是创建这些文件的基本步骤：

1. 创建MyBatis配置文件：

   在你的项目的资源目录（通常是`src/main/resources`）下，创建一个名为`mybatis-config.xml`的文件。在这个文件中，添加以下内容：

   ```xml
   <configuration>
       <settings>
           <setting name="logImpl" value="SLF4J"/>
       </settings>
   </configuration>
   ```

   这个配置会告诉MyBatis使用SLF4J进行日志记录。

2. 创建SLF4J的配置文件：

   如果你选择使用Logback作为SLF4J的实现，你需要创建一个名为`logback.xml`的文件。这个文件通常也位于你的项目的资源目录下。

   在`logback.xml`文件中，添加以下内容：

   ```xml
   <configuration>
       <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
           <encoder>
               <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
           </encoder>
       </appender>
   
       <logger name="org.mybatis" level="DEBUG"/>
       <logger name="java.sql" level="DEBUG"/>
   
       <root level="info">
           <appender-ref ref="STDOUT" />
       </root>
   </configuration>
   ```

   这个配置会告诉Logback将所有的MyBatis和SQL日志记录到控制台。

请注意，这些步骤可能需要根据你的具体环境和需求进行调整。你应该查阅MyBatis，SLF4J和Logback的官方文档，以获取更详细和准确的信息。

另外，如果你的项目使用Spring Boot，你可能需要在你的Spring Boot配置文件（如`application.properties`或`application.yml`）中指定MyBatis配置文件的位置。例如，你可以添加以下配置：

```properties
mybatis.config-location=classpath:mybatis-config.xml
```

这个配置会告诉Spring Boot在`classpath:mybatis-config.xml`的位置查找MyBatis配置文件。

**<font color="blue">添加颜色</font>**

&emsp;关于日志颜色的问题，这是因为Logback默认的日志输出格式并不包含颜色。如果你想要在控制台上看到彩色的日志，你需要在Logback的配置中添加一些ANSI颜色代码。

&emsp;你可以在`logback.xml`文件的`pattern`元素中添加颜色代码。例如，你可以使用以下配置来为不同级别的日志添加不同的颜色：

```xml
<pattern>%d{HH:mm:ss.SSS} [%thread] %highlight(%-5level) %logger{36} - %msg%n</pattern>
```

&emsp;在这个配置中，`%highlight`元素会根据日志级别改变文本的颜色。例如，`ERROR`级别的日志会被显示为红色，`WARN`级别的日志会被显示为黄色，等等。

&emsp;请注意，这个配置可能不会在所有的控制台上工作。一些控制台可能不支持ANSI颜色代码，或者需要额外的配置才能显示颜色。你应该查阅你的控制台的文档，以获取更详细和准确的信息。

![image-20230626134950225](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20230626134950225.png)

&emsp;由上图可以了解到第一次发送login请求使用了880ms而第二次就减少到了108ms。

&emsp;这个现象可能是因为数据库连接池和数据库的初始化导致的。

&emsp;在你的日志中，第一次登录请求时，你可以看到这样的日志：

```sql
13:42:01.853 [http-nio-9000-exec-2] INFO  com.zaxxer.hikari.HikariDataSource - HikariPool-1 - Starting...
13:42:02.374 [http-nio-9000-exec-2] INFO  com.zaxxer.hikari.HikariDataSource - HikariPool-1 - Start completed.
```

&emsp;这表明在第一次请求时，HikariCP（一个高性能的JDBC连接池）正在启动，并且启动完成。这个过程可能包括与数据库的连接、获取连接等操作，这些操作都需要一定的时间，因此第一次请求的时间会比较长。

&emsp;然后在第二次请求时，连接池已经启动完成，可以直接从连接池中获取到已经建立好的连接，所以时间会大大减少。

&emsp;这就是为什么第一次请求时间较长，而后续请求时间较短的原因。这是一种常见的现象，因为初始化操作通常只在第一次请求时进行，后续请求可以复用已经初始化好的资源，因此会更快。