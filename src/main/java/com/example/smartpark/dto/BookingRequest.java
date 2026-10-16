package com.example.smartpark.dto;
import java.time.LocalDate;
import java.time.LocalTime;

public class BookingRequest {
    private Long userId,slotId;
    private LocalDate bookingDate;
    private LocalTime startTime,endTime;
    private String vehicleNumber,paymentMethod;
    private Double amount;
    public BookingRequest(){}
    public Long getUserId(){return userId;} public void setUserId(Long v){userId=v;}
    public Long getSlotId(){return slotId;} public void setSlotId(Long v){slotId=v;}
    public LocalDate getBookingDate(){return bookingDate;} public void setBookingDate(LocalDate v){bookingDate=v;}
    public LocalTime getStartTime(){return startTime;} public void setStartTime(LocalTime v){startTime=v;}
    public LocalTime getEndTime(){return endTime;} public void setEndTime(LocalTime v){endTime=v;}
    public String getVehicleNumber(){return vehicleNumber;} public void setVehicleNumber(String v){vehicleNumber=v;}
    public String getPaymentMethod(){return paymentMethod;} public void setPaymentMethod(String v){paymentMethod=v;}
    public Double getAmount(){return amount;} public void setAmount(Double v){amount=v;}
}
