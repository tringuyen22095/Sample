package com.personal.ctrl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/sync")
public class SyncController {

	@GetMapping("24h-news")
	public ResponseEntity<String> syncLatestNewOn24h() {
		return ResponseEntity.ok("test");
	}
	
}
