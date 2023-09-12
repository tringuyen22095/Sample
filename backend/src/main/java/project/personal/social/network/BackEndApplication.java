package project.personal.social.network;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

import project.personal.shared.common.config.GlobalExceptionHandler;

@SpringBootApplication
@EnableEurekaClient
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
		SpringApplication.run(BackEndApplication.class, args);
	}

}
