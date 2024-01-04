package music.store.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import music.store.entity.Instrument;
import music.store.entity.Store;
import music.store.entity.StoreInventory;

@Data
@NoArgsConstructor
public class StoreInventoryData {

	private Store store;
	private Instrument instrument;
    private int inventoryAmount;
    
    public StoreInventoryData(StoreInventory storeInventory) {
    	this.inventoryAmount = storeInventory.getInventoryAmount();
    	this.store = storeInventory.getStore();
    	this.instrument = storeInventory.getInstrument();
    	
    }
    
    public StoreInventory toStoreInventory() {
    	StoreInventory storeInventory = new StoreInventory();
    	
    	storeInventory.setInventoryAmount(inventoryAmount);
    	storeInventory.setInstrument(instrument);
    	storeInventory.setStore(store);
    	
    	return storeInventory;
    }
}
