package music.store.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import music.store.controller.model.CategoryData;
import music.store.controller.model.InstrumentData;
import music.store.controller.model.StoreData;
import music.store.dao.CategoryDao;
import music.store.dao.InstrumentDao;
import music.store.dao.StoreDao;
import music.store.entity.Instrument;
import music.store.entity.Store;

@Service
public class MusicStoreService {

	@Autowired
	private StoreDao storeDao;
	
	@Autowired
	private InstrumentDao instrumentDao;
	
	@Autowired
	private CategoryDao categoryDao;

	//Store Rest Functions
	
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
		return storeDao.findById(storeId)
				.orElseThrow(() -> new NoSuchElementException("Store with ID= " + storeId + " was not found."));

	}

	@Transactional(readOnly = true)
	public List<StoreData> retrieveAllStores() {

		return storeDao.findAll().stream().map(StoreData::new).toList();
	}

	@Transactional(readOnly = false)
	public void deleteStore(Long storeId) {
		Store store = findStoreById(storeId);
		storeDao.delete(store);
		
	}
	
	//Instrument Rest Functions
	
	@Transactional(readOnly = false)
	public InstrumentData saveInstrument(InstrumentData instrumentData, Long storeId) {
		Store store = findStoreById(storeId);
		Long instrumentId = instrumentData.getInstrumentId();
		Instrument instrument = findorCreateInstrument(instrumentData, storeId, instrumentId);
		
		copyInstrumentFields(instrument, instrumentData);
		
		store.getInstruments().add(instrument);
		
		Instrument dbInstrument = instrumentDao.save(instrument);
		
		return new InstrumentData(dbInstrument);
	}
	
	private void copyInstrumentFields(Instrument instrument, InstrumentData instrumentData) {
		instrument.setName(instrumentData.getName());
		instrument.setColor(instrumentData.getColor());
		instrument.setDescription(instrumentData.getDescription());
		instrument.setPrice(instrumentData.getPrice());
		instrument.setImage(instrumentData.getImage());
		instrument.setCategory(instrumentData.getCategory().toCategory());
		
	}

	private Instrument findorCreateInstrument(InstrumentData instrumentData, Long storeId, Long instrumentId) {

		Instrument instrument;
		
		if(Objects.isNull(instrumentId)) {
			instrument = new Instrument();
		} else {
			instrument = findInstrumentById(instrumentData, storeId, instrumentId);
			
		}
		return instrument;
	}

	private Instrument findInstrumentById(InstrumentData instrumentData, Long storeId, Long instrumentId) {
		Instrument instrument = instrumentDao.findById(instrumentId).orElseThrow(() -> new NoSuchElementException("Instrument with Id= " 
				+ instrumentId + " does not exist."));
		Set<Store> store = instrument.getStores();
		
		for(Store stores : store) {
			if(stores.getStoreId() != storeId) {
				throw new IllegalArgumentException("Instrument does not belong to any specified Store");
			}
			
		}
		
			return instrument;
	}

	private Instrument findInstrumentById(Long storeId, Long instrumentId) {
		Instrument instrument = instrumentDao.findById(instrumentId).orElseThrow(() -> new NoSuchElementException("Instrument with Id= " 
				+ instrumentId + " does not exist."));
		Set<Store> store = instrument.getStores();
		
		for(Store stores : store) {
			if(stores.getStoreId() != storeId) {
				throw new IllegalArgumentException("Instrument does not belong to any specified Store");
			}
			
		}
		
			return instrument;
	}
	
	@Transactional(readOnly = true)
	public InstrumentData retrieveInstrumentById(Long instrumentId) {
		Instrument instrument = findInstrumentById(instrumentId);

		return new InstrumentData(instrument);

	}
	
	private Instrument findInstrumentById(Long instrumentId) {
		return instrumentDao.findById(instrumentId)
				.orElseThrow(() -> new NoSuchElementException("Instrument with ID= " + instrumentId + " was not found."));

	}
	
	@Transactional(readOnly = true)
	public List<InstrumentData> retrieveAllInstruments() {

		return instrumentDao.findAll().stream().map(InstrumentData::new).toList();
	}

	@Transactional(readOnly = false)
	public void deleteInstrumentById(Long instrumentId, Long storeId) {
		Instrument instrument = findInstrumentById(instrumentId, storeId);
		instrumentDao.delete(instrument);
		
	}

	@Transactional(readOnly = true)
	public List<CategoryData> retrieveAllCategories() {
		
		return categoryDao.findAll().stream().map(CategoryData::new).toList();
	}

}
