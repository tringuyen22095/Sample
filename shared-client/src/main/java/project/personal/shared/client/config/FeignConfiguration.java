package project.personal.shared.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Configuration
public class FeignConfiguration {

	@Bean
	RequestInterceptor authRequestInterceptor() {
	    return new RequestInterceptor() {
			@Override
			public void apply(RequestTemplate template) {
			    template.header("Authorization", "Bearer ");
			    template.header("AC", "test");
			}
		};
	}

}
