package com.codegorilla.Measurement;

import java.time.LocalDate;

public class Measurement {
	LocalDate insertDate;
	double powerGenerated;
	double powerConsumption;
	double powerReturn;

// Constructor
	public Measurement(LocalDate insertDate, double powerGenerated, double powerConsumption, double powerReturn) {
		this.insertDate = insertDate;
		this.powerGenerated = powerGenerated;
		this.powerConsumption = powerConsumption;
		this.powerReturn = powerReturn;
	}

    public LocalDate getInsertDate() {
    	return insertDate;
    }

    public void setInsertDate(LocalDate insertDate) {
    	this.insertDate = insertDate;
    }

    public double getPowerGenerated() {
    	return powerGenerated;
    }

    public void setPowerGenerated(double powerGenerated) {
    	this.powerGenerated = powerGenerated;
    }

    public double getPowerConsumption() {
    	return powerConsumption;
    }

    public void setPowerConsumption(double powerConsumption) {
    	this.powerConsumption = powerConsumption;
    }

    public double getPowerReturn() {
    	return powerReturn;
    }

    public void setPowerReturn(double power) {
    	this.powerReturn = power;
    }
}
 