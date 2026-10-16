package com.example.smartpark.entity;

import jakarta.persistence.*;
import java.time.*;

@Entity
@Table(name="bookings")
public class Booking {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long bookingId;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="slot_id", nullable=false)
    private ParkingSlot slot;

    @Column(nullable=false) private LocalDate bookingDate;
    @Column(nullable=false) private LocalTime startTime;
    @Column(nullable=false) private LocalTime endTime;
    private String vehicleNumber;
    private Double amount;
    private String paymentMethod;
    private String paymentStatus="PENDING";
    private String transactionId;
    private String bookingStatus="CONFIRMED";
    private LocalDateTime createdAt;

    public Booking(){}

    @PrePersist
    public void prePersist(){
        if(createdAt==null) createdAt=LocalDateTime.now();
        if(paymentStatus==null) paymentStatus="PENDING";
        if(bookingStatus==null) bookingStatus="CONFIRMED";
        if(amount==null) amount=50.0;
    }

    public Long getBookingId(){return bookingId;}
    public void setBookingId(Long v){bookingId=v;}
    public User getUser(){return user;}
    public void setUser(User v){user=v;}
    public ParkingSlot getSlot(){return slot;}
    public void setSlot(ParkingSlot v){slot=v;}
    public LocalDate getBookingDate(){return bookingDate;}
    public void setBookingDate(LocalDate v){bookingDate=v;}
    public LocalTime getStartTime(){return startTime;}
    public void setStartTime(LocalTime v){startTime=v;}
    public LocalTime getEndTime(){return endTime;}
    public void setEndTime(LocalTime v){endTime=v;}
    public String getVehicleNumber(){return vehicleNumber;}
    public void setVehicleNumber(String v){vehicleNumber=v;}
    public Double getAmount(){return amount;}
    public void setAmount(Double v){amount=v;}
    public String getPaymentMethod(){return paymentMethod;}
    public void setPaymentMethod(String v){paymentMethod=v;}
    public String getPaymentStatus(){return paymentStatus;}
    public void setPaymentStatus(String v){paymentStatus=v;}
    public String getTransactionId(){return transactionId;}
    public void setTransactionId(String v){transactionId=v;}
    public String getBookingStatus(){return bookingStatus;}
    public void setBookingStatus(String v){bookingStatus=v;}
    public LocalDateTime getCreatedAt(){return createdAt;}
    public void setCreatedAt(LocalDateTime v){createdAt=v;}
}
