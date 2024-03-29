package project.personal.social.network.entity.base;

import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Component;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

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

	private static UUID currentUserName() {
		return UUID.randomUUID();
	}
}