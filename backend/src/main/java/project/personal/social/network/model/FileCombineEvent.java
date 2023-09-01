package project.personal.social.network.model;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileCombineEvent extends ApplicationEvent {

	private static final long serialVersionUID = 3893269436003097118L;

	public FileCombineEvent(Object source, String fileName, Long chunkIndex, Long fileSize, Long totalChunks) {
		super(source);
		this.fileName = fileName;
		this.chunkIndex = chunkIndex;
		this.fileSize = fileSize;
		this.totalChunks = totalChunks;
	}

	private String fileName;

	private Long chunkIndex;

	private Long fileSize;

	private Long totalChunks;

}
