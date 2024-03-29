package project.personal.shared.client.resource;

import java.util.List;
import java.util.UUID;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;
import project.personal.shared.client.config.FeignConfiguration;
import project.personal.shared.client.config.LoadBalancerConfiguration;
import project.personal.shared.common.exception.EntityNotFoundException;
import project.personal.shared.common.model.req.RoomReq;
import project.personal.shared.common.model.res.RoomDetailRes;
import project.personal.shared.common.model.res.RoomRes;

@FeignClient(name = "gateway", contextId = "RoomFeignClients", path = "/resource/room", configuration = FeignConfiguration.class)
@LoadBalancerClient(configuration = { LoadBalancerConfiguration.class })
public interface RoomFeignClient {

	@GetMapping
	ResponseEntity<List<RoomRes>> getRooms();

	@GetMapping("/{id}")
	ResponseEntity<RoomDetailRes> getRoomDetail(@PathVariable("id") UUID id) throws EntityNotFoundException;

	@PostMapping
	ResponseEntity<RoomRes> createRoom(@Valid @RequestBody RoomReq req);

	@DeleteMapping("/{id}")
	ResponseEntity<Void> deleteRoom(@PathVariable("id") UUID id) throws EntityNotFoundException;

	@PutMapping("/{id}")
	ResponseEntity<Void> updateRoom(@PathVariable("id") UUID id, @Valid @RequestBody RoomReq req)
			throws EntityNotFoundException;

}
