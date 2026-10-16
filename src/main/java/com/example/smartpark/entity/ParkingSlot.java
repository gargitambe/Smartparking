package com.example.smartpark.entity;

import jakarta.persistence.*;

@Entity
@Table(name="parking_slots")
public class ParkingSlot {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long slotId;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="location_id", nullable=false)
    private ParkingLocation location;

    @Column(nullable=false) private String slotNumber;
    private String slotType;
    private String status="AVAILABLE";

    public ParkingSlot(){}

    public Long getSlotId(){return slotId;}
    public void setSlotId(Long v){slotId=v;}
    public ParkingLocation getLocation(){return location;}
    public void setLocation(ParkingLocation v){location=v;}
    public String getSlotNumber(){return slotNumber;}
    public void setSlotNumber(String v){slotNumber=v;}
    public String getSlotType(){return slotType;}
    public void setSlotType(String v){slotType=v;}
    public String getStatus(){return status;}
    public void setStatus(String v){status=v;}
}
