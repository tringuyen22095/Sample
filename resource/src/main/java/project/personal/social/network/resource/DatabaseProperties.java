package project.personal.social.network.resource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@ConfigurationProperties(prefix = "spring.datasource")
@Configuration
@Data
public class DatabaseProperties {

	private String url;

	private String username;

	private String password;

}
