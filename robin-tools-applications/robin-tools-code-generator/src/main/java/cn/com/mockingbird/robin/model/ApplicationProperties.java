package cn.com.mockingbird.robin.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 应用属性
 *
 * @author zhao peng
 * @date 2024/1/30 1:19
 **/
@Data
@ConfigurationProperties(prefix = "spring.app")
public class ApplicationProperties {
    private String name;
    private String version;
}
