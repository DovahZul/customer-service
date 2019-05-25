package app.base.model;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

public interface WorkerRepository extends Repository<Worker, Integer> {

    @Query("SELECT worker FROM Worker worker ORDER BY worker.lastName")
    @Transactional(readOnly = true)
    List<Worker> findWorkers();

    @Transactional(readOnly = true)
    Worker findById(Integer id);

    void save(Worker worker);

}

