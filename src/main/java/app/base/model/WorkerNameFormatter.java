package app.base.model;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

/**
 *
 *  https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/core.html#format
 */
@Component
public class WorkerNameFormatter implements Formatter<Worker> {

    private final WorkerRepository workers;


    @Autowired
    public WorkerNameFormatter(WorkerRepository workers) {
        this.workers = workers;
    }

    @Override
    public String print(Worker worker, Locale locale) {
        return worker.getFullName();
    }
    
    @Override
    public Worker parse(String text, Locale locale) throws ParseException {
        Collection<Worker> findWorkers = this.workers.findWorkers();
          for (Worker worker : findWorkers) {
            //if (worker.getFullName().equals(text)) {
    	        return worker;
            //}
        }
        throw new ParseException("worker name not found: " + text, 0);
    }

}
