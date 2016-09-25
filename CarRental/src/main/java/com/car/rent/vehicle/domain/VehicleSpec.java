package com.car.rent.vehicle.domain;

public class VehicleSpec {
	private String brand;
	private String type;
	private String vehiclePlateNumber;
	private Integer numberOfSeats;

	private Boolean isAvailable;
	private Integer model;
	private Double dailyPrice;

	public String getQuery() {
		StringBuffer query = new StringBuffer("SELECT v FROM Vehicle v WHERE ");
		if (brand != null) {
			query.append(" v.brand LIKE '%");
			query.append(brand);
			query.append("%'");
		}
		if (type != null) {
			query.append(" v.type LIKE '%");
			query.append(type);
			query.append("%'");
		}
		if (model != null) {
			query.append(" v.model = ");
			query.append(model);
		}
		if (vehiclePlateNumber != null) {
			query.append(" v.vehiclePlateNumber LIKE '%");
			query.append(vehiclePlateNumber);
			query.append("%'");
		}

		if (numberOfSeats != null) {
			query.append(" v.numberOfSeats <= ");
			query.append(numberOfSeats);
		}
		if (dailyPrice != null) {
			query.append(" v.dailyPrice <= ");
			query.append(dailyPrice);
		}
		if (dailyPrice != null) {
			query.append(" v.dailyPrice <= ");
			query.append(dailyPrice);
		}
		if (dailyPrice != null) {
			query.append(" v.isAvailable = ");
			query.append(dailyPrice);
		}
		return query.toString();
	}

	public String getBrand() {
		if (brand == null) {
			brand = "%%";
		}
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = "%" + brand + "%";
	}

	public String getType() {
		if (type == null) {
			type = "%%";
		}
		return type;
	}

	public void setType(String type) {
		this.type = "%" + type + "%";
	}

	public Integer getModel() {
		return model;
	}

	public void setModel(Integer model) {
		this.model = model;
	}

	public String getVehiclePlateNumber() {
		if (vehiclePlateNumber == null) {
			vehiclePlateNumber = "%%";
		}
		return vehiclePlateNumber;
	}

	public void setVehiclePlateNumber(String vehiclePlateNumber) {
		this.vehiclePlateNumber = "%" + vehiclePlateNumber + "%";
	}

	public Integer getNumberOfSeats() {
		if (numberOfSeats == null) {
			numberOfSeats = 0;
		}
		return numberOfSeats;
	}

	public void setNumberOfSeats(Integer numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	public Double getDailyPrice() {
		if (dailyPrice == null) {
			dailyPrice = 0.0;
		}
		return dailyPrice;
	}

	public void setDailyPrice(Double dailyPrice) {
		this.dailyPrice = dailyPrice;
	}

	public Boolean getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

}
