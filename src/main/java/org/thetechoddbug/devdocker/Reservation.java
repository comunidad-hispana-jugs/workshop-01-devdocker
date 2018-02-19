package org.thetechoddbug.devdocker;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "reservation_owner")
	private String reservationOwner;

	@Column(name = "restaurant_name")
	private String restaurantName;

	@Column(name = "reservation_date")
	private Date reservationDate;

	@Column(name = "diners_number")
	private String dinersNumber;

	@Column(name = "confirmed")
	private Boolean confirmed;

	protected Reservation() {
	}

	public Reservation(String reservationOwner, String restaurantName, Date reservationDate, String dinersNumber,
			Boolean confirmed) {
		this.reservationOwner = reservationOwner;
		this.restaurantName = restaurantName;
		this.reservationDate = reservationDate;
		this.dinersNumber = dinersNumber;
		this.confirmed = confirmed;
	}

	public Reservation(String reservationOwner, String restaurantName, LocalDate reservationLocalDate,
			String dinersNumber, Boolean confirmed) {
		this.reservationOwner = reservationOwner;
		this.restaurantName = restaurantName;
		if (reservationLocalDate != null) {
			this.reservationDate = Date
					.from(reservationLocalDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		}
		this.dinersNumber = dinersNumber;
		this.confirmed = confirmed;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public Date getReservationDate() {
		return this.reservationDate;
	}

	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}

	public LocalDate getReservationLocalDate() {
		return this.reservationDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public void setReservationLocalDate(LocalDate reservationLocalDate) {
		this.reservationDate = Date
				.from(reservationLocalDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

	public String getDinersNumber() {
		return dinersNumber;
	}

	public void setDinersNumber(String dinersNumber) {
		this.dinersNumber = dinersNumber;
	}

	public Boolean getConfirmed() {
		return confirmed;
	}

	public void setConfirmed(Boolean confirmed) {
		this.confirmed = confirmed;
	}

	public String getReservationOwner() {
		return reservationOwner;
	}

	public void setReservationOwner(String reservationOwner) {
		this.reservationOwner = reservationOwner;
	}

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", reservationOwner=" + reservationOwner + ", restaurantName=" + restaurantName
				+ ", reservationDate=" + reservationDate + ", dinersNumber=" + dinersNumber + ", confirmed=" + confirmed
				+ "]";
	}


}
