package app.base.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "workers")
public class Worker extends Person {
	
	public String getFullName() {
		return this.getLastName() + this.getFirstName();
	}
}
