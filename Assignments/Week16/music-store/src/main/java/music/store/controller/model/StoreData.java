package music.store.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import music.store.entity.Instrument;
import music.store.entity.Store;

@Data
@NoArgsConstructor
public class StoreData {


	private Long storeId;
    private String storeName;
	private String streetAddress;
	private String city;
	private String state;
	private String zip;
	private String phone;
	private Set<InstrumentData> instruments = new HashSet<>();
	
    public StoreData(Store store) {
    	this.storeId = store.getStoreId();
    	this.storeName = store.getStoreName();
    	this.streetAddress = store.getStreetAddress();
    	this.city = store.getCity();
    	this.state = store.getState();
    	this.zip = store.getZip();
    	this.phone = store.getPhone();
    	
    	for(Instrument instrument : store.getInstruments()) {
    		this.instruments.add(new InstrumentData(instrument));
    	}
    	
 }
    
    public Store toStore() {
    	Store store = new Store();
    	store.setStoreId(storeId);
    	store.setStoreName(storeName);
    	store.setStreetAddress(streetAddress);
    	store.setCity(city);
    	store.setState(state);
    	store.setZip(zip);
    	store.setPhone(phone);
    	
    	for(InstrumentData instrument : instruments) {
    		store.getInstruments().add(instrument.toInstrument());
    	}
    	
  	
    	return store;
    }
   
}
