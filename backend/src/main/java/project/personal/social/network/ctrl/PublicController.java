package project.personal.social.network.ctrl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicController {

	@GetMapping("/health")
	public ResponseEntity<String> checkHealth() {
		return ResponseEntity.ok("healthy!!!");
	}
	
}
