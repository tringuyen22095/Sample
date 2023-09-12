package project.personal.shared.client.resource;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import project.personal.shared.client.config.FeignConfiguration;
import project.personal.shared.client.config.LoadBalancerConfiguration;
import project.personal.shared.common.exception.EntityNotFoundException;
import project.personal.shared.common.model.req.MessageReq;
import project.personal.shared.common.model.res.MessageRes;

@FeignClient(name = "gateway", contextId = "MessageFeignClient", path = "/resource/message", configuration = FeignConfiguration.class)
@LoadBalancerClient(configuration = { LoadBalancerConfiguration.class })
public interface MessageFeignClient {

	@GetMapping("/{roomId}")
	ResponseEntity<Page<MessageRes>> getMessage(@PathVariable("roomId") UUID roomId,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size,
			@RequestParam(name = "direction", defaultValue = "DESC") Direction direction,
			@RequestParam(name = "properties", defaultValue = "updatedOn, createdOn") String... properties)
			throws EntityNotFoundException;

	@PostMapping
	ResponseEntity<MessageRes> createMessage(@Valid @RequestBody MessageReq req) throws EntityNotFoundException;

	@DeleteMapping("{id}")
	ResponseEntity<Void> deleteMessage(@PathVariable("id") UUID id) throws EntityNotFoundException;

	@PutMapping("/{id}")
	ResponseEntity<Void> updateRoom(@PathVariable("id") UUID id, @Valid @RequestBody MessageReq req)
			throws EntityNotFoundException;

}
