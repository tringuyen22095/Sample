package project.personal.social.network.repo;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import project.personal.social.network.entity.MessageEntity;

public interface MessageRepository extends JpaRepository<MessageEntity, UUID> {

	@Query(value = "select m from MessageEntity m where m.room.id = ?1")
	Page<MessageEntity> findAllByRoomId(UUID roomId, Pageable pageable);

}
