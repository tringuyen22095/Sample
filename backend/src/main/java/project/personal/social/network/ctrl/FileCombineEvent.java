package project.personal.social.network.ctrl;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileCombineEvent extends ApplicationEvent {
	private static final long serialVersionUID = 3893269436003097118L;

	public FileCombineEvent(Object source, String fileName, Integer chunkIndex, Long fileSize) {
		super(source);
		this.fileName = fileName;
		this.chunkIndex = chunkIndex;
		this.fileSize = fileSize;
	}

	private String fileName;

	private Integer chunkIndex;

	private Long fileSize;
}
