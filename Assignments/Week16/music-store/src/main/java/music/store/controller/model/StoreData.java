package music.store.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import music.store.entity.Store;
import music.store.entity.StoreInventory;

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
    private Set<StoreInventoryData> inventoryList = new HashSet<>();
	
    public StoreData(Store store) {
    	this.storeId = store.getStoreId();
    	this.storeName = store.getStoreName();
    	this.streetAddress = store.getStreetAddress();
    	this.city = store.getCity();
    	this.state = store.getState();
    	this.zip = store.getZip();
    	this.phone = store.getPhone();
    	
    	for(StoreInventory inventory : store.getInventoryList()) {
    		this.inventoryList.add(new StoreInventoryData(inventory));
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
    	
    	for(StoreInventoryData inventoryData: inventoryList) {
    		store.getInventoryList().add(inventoryData.toStoreInventory());
    	}
    	
    	return store;
    }
   
}
