package project.personal.social.network.config;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import project.personal.social.network.resource.DatabaseProperties;

@Configuration
@RequiredArgsConstructor
public class FlywayConfiguration {
	
	private final DatabaseProperties databaseProperties;
	
	@Bean
	public void flywayInit() {
		Flyway.configure()
				.dataSource(this.databaseProperties.getUrl(), this.databaseProperties.getUsername(), this.databaseProperties.getPassword())
				.load();
	}
	
}
