package project.personal.shared.client.resource;

import java.util.UUID;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import project.personal.shared.client.config.FeignConfiguration;
import project.personal.shared.client.config.LoadBalancerConfiguration;
import project.personal.shared.common.exception.EntityNotFoundException;
import project.personal.shared.common.model.req.DocumentReq;
import project.personal.shared.common.model.res.DocumentRes;

@FeignClient(name = "gateway", contextId = "DocumentFeignClient", path = "/resource/document", configuration = FeignConfiguration.class)
@LoadBalancerClient(configuration = { LoadBalancerConfiguration.class })
public interface DocumentFeignClient {

	@GetMapping("/{roomId}")
	public ResponseEntity<Page<DocumentRes>> documentPagingByRoomId(@PathVariable("roomId") UUID roomId,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size,
			@RequestParam(name = "direction", defaultValue = "DESC") Direction direction,
			@RequestParam(name = "properties", defaultValue = "updatedOn, createdOn") String... properties)
			throws EntityNotFoundException;

	@PutMapping("/{docId}")
	public void updateDocument(@PathVariable("docId") UUID id, @RequestBody DocumentReq req)
			throws EntityNotFoundException;

}
