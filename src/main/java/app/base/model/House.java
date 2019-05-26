package app.base.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.format.annotation.DateTimeFormat;

/*

 * CREATE TABLE accounts(
id INTEGER IDENTITY PRIMARY KEY,
currency_type_id INTEGER NOT NULL,
ballance DOUBLE NOT NULL,
customer_id INTEGER NOT NULL
);
 * 
 * 
 * */
/*
CREATE TABLE houses (
		
		kadastr VARCHAR(20),
		address VARCHAR(20),
		disctrict VARCHAR(15),
		land VARCHAR(10),
		year INTEGER,
		material VARCHAR(20),
		base VARCHAR(20),
		comment VARCHAR(50),
		wear FLOAT,
		flow INTEGER,
		line FLOAT,
		quare FLOAT,
		flats INTEGER,
		elevator BOOLEAN,
		flat INTEGER,
		storey INTEGER,
		rooms INTEGER,
		square_flat FLOAT,
		dwell FLOAT,
		branch FLOAT,
		balcony FLOAT,
		height FLOAT,
		recorrd INTEGER,
		document VARCHAR(30),
		date_doc VARCHA(40),
		fio_host VARCHAR(50),
		passport VARCHAR(50),
		part FLOAT,
		born INTEGER
	);
*/
@Entity
@Table(name = "houses")
public class House extends BaseEntity {

    @Column(name = "kadastr")
    private String kadastr;
    
    public String getKadastr() {
        return this.kadastr;
    }

    public void setKadastr(String value) {
        this.kadastr = value;
    } 
    
    @Column(name = "address")
    private String address;
    
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String value) {
        this.address = value;
    } 
    
    
    @Column(name = "district")
    private String district;
    
    public String getDistrict() {
        return this.district;
    }

    public void setDistrict(String value) {
        this.district = value;
    } 
    
    @Column(name = "land")
    private String land;
    
    public String getLand() {
        return this.land;
    }

    public void setLand(String value) {
        this.land = value;
    } 
    
    @Column(name = "year")
    private Integer year;
    
    public Integer getYear() {
        return this.year;
    }

    public void setYear(Integer value) {
        this.year = value;
    } 
    
    @Column(name = "material")
    private String material;
    
    public String getMaterial() {
        return this.material;
    }

    public void setMaterial(String value) {
        this.material = value;
    } 
    
    @Column(name = "base")
    private String base;
    
    public String getBase() {
        return this.base;
    }

    public void setBase(String value) {
        this.base = value;
    } 
    
    @Column(name = "comment")
    private String comment;
    
    public String getComment() {
        return this.comment;
    }

    public void setComment(String value) {
        this.comment = value;
    } 
    
    @Column(name = "wear")
    private Float wear;
    
    public Float getWear() {
        return this.wear;
    }

    public void setWear(Float value) {
        this.wear = value;
    } 
    
    @Column(name = "flow")
    private Integer flow;
    
    public Integer getFlow() {
        return this.flow;
    }

    public void setFlow(Integer value) {
        this.flow = value;
    } 
    
    @Column(name = "line")
    private Float line;
    
    public Float getLine() {
        return this.line;
    }

    public void setLine(Float value) {
        this.line = value;
    } 
    
    @Column(name = "square")
    private Float square;
    
    public Float getSquare() {
        return this.square;
    }

    public void setSquare(Float value) {
        this.square = value;
    } 
    
    @Column(name = "flats")
    private Integer flats;
    
    public Integer geFlats() {
        return this.flats;
    }

    public void setFlats(Integer value) {
        this.flats = value;
    } 
    
    @Column(name = "elevator")
    private Boolean elevator;
    
    public Boolean getElevator() {
        return this.elevator;
    }

    public void setElevator(Boolean value) {
        this.elevator = value;
    } 
    
    @Column(name = "storey")
    private Integer storey;
    
    public Integer getStorey() {
        return this.storey;
    }

    public void setStorey(Integer value) {
        this.storey = value;
    } 
    
    @Column(name = "rooms")
    private Integer rooms;
    
    public Integer getRooms() {
        return this.rooms;
    }

    public void setRooms(Integer value) {
        this.rooms = value;
    } 
    
    @Column(name = "square_flat")
    private Float squareFlat;
    
    public Float getSquareFlat() {
        return this.squareFlat;
    }

    public void setSquareFlat(Float value) {
        this.squareFlat = value;
    } 
    
    @Column(name = "dwell")
    private Float dwell;
    
    public Float getDwell() {
        return this.dwell;
    }

    public void setDwell(Float value) {
        this.dwell = value;
    } 
    
    @Column(name = "branch")
    private Float branch;
    
    public Float getBranch() {
        return this.branch;
    }

    public void setBranch(Float value) {
        this.branch = value;
    } 
    
    @Column(name = "balcony")
    private Float balcony;
    
    public Float getBalcony() {
        return this.balcony;
    }

    public void setBalcony(Float value) {
        this.balcony = value;
    } 
    
    @Column(name = "height")
    private Float height;
    
    public Float getHeight() {
        return this.height;
    }

    public void setHeight(Float value) {
        this.height = value;
    } 
    
    @Column(name = "recorrd")
    private Integer recorrd;
    
    public Integer getRecord() {
        return this.recorrd;
    }

    public void setRecord(Integer value) {
        this.recorrd = value;
    } 
    
    @Column(name = "document")
    private String document;
    
    public String getDocument() {
        return this.document;
    }

    public void setDocument(String value) {
        this.document = value;
    } 
    
    @Column(name = "date_doc")
    private String dateDoc;
    
    public String getDateDoc() {
        return this.dateDoc;
    }

    public void setDateDoc(String value) {
        this.dateDoc = value;
    } 
    
    @Column(name = "fio_host")
    private String fioHost;
    
    public String getFioHost() {
        return this.fioHost;
    }

    public void getFioHost(String value) {
        this.fioHost = value;
    } 
    
    @Column(name = "passport")
    private String passport;
    
    public String getPassport() {
        return this.passport;
    }

    public void setPassport(String value) {
        this.passport = value;
    } 
    
    @Column(name = "part")
    private Float part;
    
    public Float getPart() {
        return this.part;
    }

    public void setPart(Float value) {
        this.part = value;
    } 
    
    @Column(name = "born")
    private Integer born;
    
    public Integer getBorn() {
        return this.born;
    }

    public void setBorn(Integer value) {
        this.born = value;
    } 


}
