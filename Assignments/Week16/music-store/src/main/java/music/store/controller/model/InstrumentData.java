package music.store.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import music.store.entity.Category;
import music.store.entity.Instrument;
import music.store.entity.StoreInventory;

@Data
@NoArgsConstructor
public class InstrumentData {

	private Long instrumentId;
	private String name;
	private String color;
	private String description;
	private Long price;
	private String image;
	private Category category;
	private Set<StoreInventoryData> inventoryList = new HashSet<>();
	
	public InstrumentData(Instrument instrument) {
		
		this.instrumentId = instrument.getInstrumentId();
		this.name = instrument.getName();
		this.color = instrument.getColor();
		this.description = instrument.getDescription();
		this.price = instrument.getPrice();
		this.image = instrument.getImage();
		this.category = instrument.getCategory();
		
		for(StoreInventory inventory : instrument.getInventoryList()) {
    		this.inventoryList.add(new StoreInventoryData(inventory));
    	}
	}
	
	public Instrument toInstrument() {
		Instrument instrument = new Instrument();
		
		instrument.setInstrumentId(instrumentId);
		instrument.setName(name);
		instrument.setColor(color);
		instrument.setDescription(description);
		instrument.setPrice(price);
		instrument.setImage(image);
		instrument.setCategory(category);
		
		for(StoreInventoryData inventoryData: inventoryList) {
			instrument.getInventoryList().add(inventoryData.toStoreInventory());
    	}
		
		return instrument;
	}
	
}
