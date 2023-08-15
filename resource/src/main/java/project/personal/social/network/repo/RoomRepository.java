package project.personal.social.network.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import project.personal.social.network.entity.RoomEntity;

public interface RoomRepository extends JpaRepository<RoomEntity, UUID> {

}
