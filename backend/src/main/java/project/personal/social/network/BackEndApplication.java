package project.personal.social.network;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

import project.personal.shared.common.config.GlobalExceptionHandler;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {
		"project.personal.shared.client.resource"
})
@EnableAsync
@ComponentScan(basePackages = {
		"project.personal.social.network"
})
@Import(GlobalExceptionHandler.class)
public class BackEndApplication {

	public static void main(String[] args) {
        final SpringApplication app = new SpringApplication(BackEndApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
	}

}
