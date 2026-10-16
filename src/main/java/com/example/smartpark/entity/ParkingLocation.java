package com.example.smartpark.entity;

import jakarta.persistence.*;

@Entity
@Table(name="parking_locations")
public class ParkingLocation {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long locationId;

    @Column(nullable=false) private String locationName;
    @Column(nullable=false) private String address;
    private String city;
    private Integer totalSlots;
    @Column(length=1000) private String description;
    @Column(length=1000) private String googleMapsUrl;
    private String status="ACTIVE";

    public ParkingLocation(){}

    public Long getLocationId(){return locationId;}
    public void setLocationId(Long v){locationId=v;}
    public String getLocationName(){return locationName;}
    public void setLocationName(String v){locationName=v;}
    public String getAddress(){return address;}
    public void setAddress(String v){address=v;}
    public String getCity(){return city;}
    public void setCity(String v){city=v;}
    public Integer getTotalSlots(){return totalSlots;}
    public void setTotalSlots(Integer v){totalSlots=v;}
    public String getDescription(){return description;}
    public void setDescription(String v){description=v;}
    public String getGoogleMapsUrl(){return googleMapsUrl;}
    public void setGoogleMapsUrl(String v){googleMapsUrl=v;}
    public String getStatus(){return status;}
    public void setStatus(String v){status=v;}
}
