package project.personal.social.network.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

@Configuration
public class CustomConfiguration {

	@Bean
	TaskExecutor taskExecutor() {
		return new SyncTaskExecutor();
	}

}
