package music.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import music.store.entity.Category;

public interface CategoryDao extends JpaRepository<Category, Long> {

}
