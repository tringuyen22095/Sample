package project.personal.social.network.config;

import org.springframework.cloud.openfeign.support.PageJacksonModule;
import org.springframework.cloud.openfeign.support.SortJacksonModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Configuration
public class FeignConfiguration {

	@Bean
	ObjectMapper objectMapper() {
		return new ObjectMapper()
		        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
		        .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
		        .registerModule(new PageJacksonModule())
		        .registerModule(new SortJacksonModule());
	}

	@Bean
	RequestInterceptor basicAuthRequestInterceptor() {
	    return new RequestInterceptor() {
			@Override
			public void apply(RequestTemplate template) {
			    template.header("Authorization", "Bearer ");
			}
		};
	}

}
