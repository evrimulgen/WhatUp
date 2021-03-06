package com.whatsup.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vividsolutions.jts.geom.Point;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by mosesfranco on 7/5/17
 * Codeup
 * Pinnacles
 */

@Entity
@Table(name = "locations")
public class Location implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column
	private String name;

	@Column
	private String yelpId;

	@OneToOne
	private Vendor vendor;

	@Column(name = "location", columnDefinition = "geometry")
	@JsonIgnore
	private Point location;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name="location_has_coupons",
			joinColumns={@JoinColumn(name="location_id")},
			inverseJoinColumns={@JoinColumn(name="coupon_id")}
	)
	@JsonIgnore
	private List<Coupon> couponList;

	@Column
	private String streetAddress;

	@Column
	private String phoneNumber;

	@Column
	private String yelpUrl;

	@Column
	private String imageUrl;

	@Column
	private double x;

	@Column
	private double y;

	public Location() {
	}

	public Location(Vendor vendor, Point location) {
		this.vendor = vendor;
		this.location = location;
	}

	public Location(String name, String yelpId, Point location, String streetAddress, String phoneNumber, String yelpUrl, String imageUrl) {
		this.name = name;
		this.yelpId = yelpId;
		this.location = location;
		this.streetAddress = streetAddress;
		this.phoneNumber = phoneNumber;
		this.yelpUrl = yelpUrl;
		this.imageUrl = imageUrl;
	}

	@JsonProperty("x")
	public double getX() {
		return this.location.getX();
	}


	public void setX(double x) {
		this.x = x;
	}

	@JsonProperty("y")
	public double getY() {
		return this.location.getY();
	}

	public void setY(double y) {
		this.y = y;
	}

	public Point getLocation() {
		return location;
	}

	public String getYelpId() {
		return yelpId;
	}

	public void setYelpId(String yelpId) {
		this.yelpId = yelpId;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public List<Coupon> getCouponList() {
		return couponList;
	}

	public void setCouponList(List<Coupon> couponList) {
		this.couponList = couponList;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("address")
	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	@JsonProperty("phone")
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@JsonProperty("url")
	public String getYelpUrl() {
		return yelpUrl;
	}

	public void setYelpUrl(String yelpUrl) {
		this.yelpUrl = yelpUrl;
	}

	@JsonProperty("image")
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}
