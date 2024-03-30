package project.personal.social.network;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
public class SpringCloudApplication {

	public static void main(String[] args) {
        final SpringApplication app = new SpringApplication(SpringCloudApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
	}

}
