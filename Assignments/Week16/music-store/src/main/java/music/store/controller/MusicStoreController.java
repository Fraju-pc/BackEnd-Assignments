package music.store.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import music.store.controller.model.InstrumentData;
import music.store.controller.model.StoreData;
import music.store.service.MusicStoreService;

@RestController
@RequestMapping("/music_store")
@Slf4j
public class MusicStoreController {
	
	@Autowired
	private MusicStoreService musicStoreService;
	
	//Store Rest Functions
	
	@PostMapping("/store")
	@ResponseStatus(code = HttpStatus.CREATED)
	public StoreData createStore(@RequestBody StoreData storeData) {
		log.info("Creating new Store {}", storeData);
		
		return musicStoreService.saveStore(storeData);
	}
	
	@PutMapping("/store/{storeId}")
	public StoreData updateStore(@PathVariable Long storeId, @RequestBody StoreData storeData) {
		storeData.setStoreId(storeId);
		log.info("Updating Store {}", storeData);
		return musicStoreService.saveStore(storeData);
	}
	
	@GetMapping("/store/{storeId}")
	public StoreData retrieveStore(@PathVariable Long storeId) {
		log.info("Retrieving store with Id={}", storeId);
		
		return musicStoreService.retrieveStoreById(storeId);
	}
	
	@GetMapping("/store")
	public List<StoreData> retrieveAllStores(){
		log.info("Retrieving all Stores");
		
		return musicStoreService.retrieveAllStores();
	}
	
	@DeleteMapping("/store/{storeId}")
	public Map<String, String> deleteStore(@PathVariable Long storeId){
		log.info("Deleting Store with Id= " + storeId + ".");
		musicStoreService.deleteStore(storeId);
		
		return Map.of("message", "Store with Id= " + storeId + " was deleted succesfully.");
	}
	
	//Instrument Rest Functions 
	
	@PostMapping("/store/{storeId}/instrument")
	@ResponseStatus(code = HttpStatus.CREATED)
	public InstrumentData createInstrument(@RequestBody InstrumentData instrumentData, @PathVariable Long storeId) {
		log.info("Creating new Instrument {}", instrumentData);
		
		return musicStoreService.saveInstrument(instrumentData, storeId);
	}
	
	@GetMapping("/instrument/{instrumentId}")
	public InstrumentData retrieveInstrument(@PathVariable Long instrumentId) {
		log.info("Retrieving Instrument with Id={}", instrumentId);
		
		return musicStoreService.retrieveInstrumentById(instrumentId);
	}
	
	@GetMapping("/instrument")
	public List<InstrumentData> retrieveAllInstruments(){
		log.info("Retrieving all Instruments");
		
		return musicStoreService.retrieveAllInstruments();
	}
}
