package music.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import music.store.controller.model.StoreData;
import music.store.service.MusicStoreService;

@RestController
@RequestMapping("/music_store")
@Slf4j
public class MusicStoreController {
	
	@Autowired
	private MusicStoreService musicStoreService;
	
	@PostMapping("/Store")
	@ResponseStatus(code = HttpStatus.CREATED)
	public StoreData createStore(@RequestBody StoreData storeData) {
		log.info("Creating new Store {}", storeData);
		
		return musicStoreService.saveStore(storeData);
	}
	
	@GetMapping("/Store/{storeId}")
	public StoreData retrieveStore(@PathVariable Long storeId) {
		log.info("Retrieving store with Id={}", storeId);
		
		return musicStoreService.retrieveStoreById(storeId);
	}
}
