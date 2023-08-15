package project.personal.social.network.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import project.personal.social.network.entity.MessageEntity;

public interface MessageRepository extends JpaRepository<MessageEntity, UUID> {

}
