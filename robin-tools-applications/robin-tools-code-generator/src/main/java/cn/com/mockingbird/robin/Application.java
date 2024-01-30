package cn.com.mockingbird.robin;

import cn.com.mockingbird.robin.common.util.IpUtils;
import cn.com.mockingbird.robin.model.ApplicationProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 应用程序入口
 */
@Slf4j
@EnableConfigurationProperties(ApplicationProperties.class)
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ApplicationContext application = SpringApplication.run(Application.class, args);
        Environment environment = application.getEnvironment();
        String ip;
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        String port = environment.getProperty("server.port");
        String appName = environment.getProperty("spring.application.name");

        String msg = "\n---------------------------------------------------------------------------"
                + "\n\tApplication "+ appName + " is running! Access URLs:"
                + "\n\tLocal: \t\thttp://127.0.0.1:" + port
                + "\n\tExternal: \thttp://" + ip + ":" + port
                + "\n---------------------------------------------------------------------------"
                ;
        log.info(msg);
    }

}