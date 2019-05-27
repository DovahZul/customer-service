package app.base.model;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface HouseRepository extends Repository<House, Integer> {

	@Query("SELECT house FROM House house")
    @Transactional(readOnly = true)
    Collection<House> getAll();
	
	@Query("SELECT house FROM House house WHERE house.kadastr like %:request% OR house.district like %:request% OR house.address like %:request%")
	@Transactional(readOnly = true)
	Collection<House> getSearchMaches(@Param("request") String request);

    void save(House house);

}

