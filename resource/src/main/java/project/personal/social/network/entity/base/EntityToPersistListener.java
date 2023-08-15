package project.personal.social.network.entity.base;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.stereotype.Component;

@Component
public class EntityToPersistListener<E> {

	@PrePersist
	public void beforePersist(final E reference) {
		if (reference instanceof BaseEntity) {
			BaseEntity base = (BaseEntity) reference;
			base.setCreatedOn(new Date());
			base.setCreatedBy(currentUserName());

		}
	}
	
	@PreUpdate
	public void beforeUpdate(final E reference) {
		if (reference instanceof BaseEntity) {
			BaseEntity base = (BaseEntity) reference;
			base.setUpdatedOn(new Date());
			base.setUpdatedBy(currentUserName());

		}
	}

	private static String currentUserName() {
		return "";
	}
}