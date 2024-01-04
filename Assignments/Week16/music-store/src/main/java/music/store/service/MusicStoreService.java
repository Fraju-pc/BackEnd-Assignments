package music.store.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import music.store.controller.model.StoreData;
import music.store.dao.StoreDao;
import music.store.entity.Store;

@Service
public class MusicStoreService {

	@Autowired
	private StoreDao storeDao;
	
	@Transactional(readOnly = false)
	public StoreData saveStore(StoreData storeData) {
		Store store = storeData.toStore();
		Store dbStore = storeDao.save(store);
		
		return new StoreData(dbStore);
	}

	@Transactional(readOnly = true)
	public StoreData retrieveStoreById(Long storeId) {
		Store store = findStoreById(storeId);
		
		return new StoreData(store);
		
	}

	private Store findStoreById(Long storeId) {
		return storeDao.findById(storeId).orElseThrow(() -> new NoSuchElementException("Store with ID= " + storeId +" was not found."));
		
	}

}
