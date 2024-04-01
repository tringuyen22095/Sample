package project.personal.social.network.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import project.personal.social.network.model.FileCombineEvent;

@Component
@Profile("prod")
public class FileEventListener implements ApplicationListener<FileCombineEvent> {

	private static final Logger _log = LoggerFactory.getLogger(FileEventListener.class);

	@Override
	public void onApplicationEvent(FileCombineEvent event) {
		_log.info("Prod fileEventListener executed!! => not supported!!");
	}

}
