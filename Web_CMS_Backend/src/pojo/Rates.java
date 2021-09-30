package com.app.pojo;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.*;

///indpndt property
//FROM and TO location is just to verify how much cost custmr has to pay for transport
//have to create/redirect new page for this cost calculator.
@Entity
@Table(name="rates")
public class Rates {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	private int fromLocation;// location imported as branch id
	private int toLocation;// location imported as branch id
	private double pricePerKg;
	private Mode mode;
	private Date date;
	
	public Rates() {}
	
	public Rates(int fromLocation, int toLocation, double pricePerKg, Mode mode, Date date) {
		super();
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
		this.pricePerKg = pricePerKg;
		this.mode = mode;
		this.date = date;
	}

	public int getFromLocation() {
		return fromLocation;
	}

	public void setFromLocation(int fromLocation) {
		this.fromLocation = fromLocation;
	}

	public int getToLocation() {
		return toLocation;
	}

	public void setToLocation(int toLocation) {
		this.toLocation = toLocation;
	}

	public double getPricePerKg() {
		return pricePerKg;
	}

	public void setPricePerKg(double pricePerKg) {
		this.pricePerKg = pricePerKg;
	}

	public Mode getMode() {
		return mode;
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Rates [fromLocation=" + fromLocation + ", toLocation=" + toLocation + ", pricePerKg=" + pricePerKg
				+ ", mode=" + mode + ", date=" + date + "]";
	}
	
	
}
