package music.store.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
public class Instrument {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long instrumentId;
	
	private String name;
	private String color;
	private String description;
	private Long price;
	private String image;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "instrument", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<StoreInventory> inventoryList = new HashSet<>();
}
