package cn.com.mockingbird.robin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * 应用程序入口
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ApplicationContext application = SpringApplication.run(Application.class, args);
        System.out.println("Hello world!");
    }

}