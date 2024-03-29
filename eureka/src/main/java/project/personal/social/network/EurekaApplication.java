package project.personal.social.network;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {

	public static void main(String[] args) {
        final SpringApplication app = new SpringApplication(EurekaApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
	}

}
