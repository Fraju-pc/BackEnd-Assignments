package music.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import music.store.entity.Store;

public interface StoreDao extends JpaRepository<Store, Long> {

}
