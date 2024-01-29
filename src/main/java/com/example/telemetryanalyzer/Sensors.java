package com.example.telemetryanalyzer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Timestamp;

@Entity
public class Sensors {


	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	private Timestamp timestamp;
	private Double press;

	private String position;

	private Double temp;
	private Double omega;
	private Double speed;
	private String car_id;

	public Sensors() {
	}


	public Sensors(Timestamp timestamp, Double press, String position, Double temp, Double omega, Double speed, String car_id) {
		this.timestamp = timestamp;
		this.press = press;
		this.position = position;
		this.temp = temp;
		this.omega = omega;
		this.speed = speed;
		this.car_id = car_id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public Double getPress() {
		return press;
	}

	public void setPress(Double press) {
		this.press = press;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Double getTemp() {
		return temp;
	}

	public void setTemp(Double temp) {
		this.temp = temp;
	}

	public Double getOmega() {
		return omega;
	}

	public void setOmega(Double omega) {
		this.omega = omega;
	}

	public Double getSpeed() {
		return speed;
	}

	public void setSpeed(Double speed) {
		this.speed = speed;
	}

	public String getCar_id() {
		return car_id;
	}

	public void setCar_id(String car_id) {
		this.car_id = car_id;
	}
}
