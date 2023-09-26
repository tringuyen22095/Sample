package project.personal.social.network.repo;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import project.personal.social.network.entity.DocumentEntity;

public interface DocumentRepository extends JpaRepository<DocumentEntity, UUID> {

	@Query(value = "select d "
			+ "from DocumentEntity d "
			+ "join d.messages m "
			+ "where m.room.id = ?1")
	Page<DocumentEntity> pagingByRoomId(UUID roomId, Pageable pageable);

}
