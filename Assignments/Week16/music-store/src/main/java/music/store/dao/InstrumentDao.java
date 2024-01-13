package music.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import music.store.entity.Instrument;

public interface InstrumentDao extends JpaRepository<Instrument, Long> {

}
