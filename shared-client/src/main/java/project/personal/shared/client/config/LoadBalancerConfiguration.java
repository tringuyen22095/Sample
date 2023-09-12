package project.personal.shared.client.config;

import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadBalancerConfiguration {

    @Bean
    ServiceInstanceListSupplier instanceSupplier(ConfigurableApplicationContext context) {
		return ServiceInstanceListSupplier.builder().withDiscoveryClient().withHealthChecks().build(context);
	}
	
}
