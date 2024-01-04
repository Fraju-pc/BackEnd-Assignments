package music.store.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import music.store.entity.Category;

@Data
@NoArgsConstructor
public class CategoryData {

	private Long categoryId;
    private String categoryName;
	
    public CategoryData(Category category) {
    	this.categoryId = category.getCategoryId();
    	this.categoryName = category.getCategoryName();
    }
	
	public Category toCategory() {
		Category category = new Category();
		
		category.setCategoryId(categoryId);
		category.setCategoryName(categoryName);
		
		return category;
	}
}
