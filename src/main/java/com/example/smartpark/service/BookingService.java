package com.example.smartpark.service;
import com.example.smartpark.entity.*;
import com.example.smartpark.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalTime;
import java.util.List;

@Service
public class BookingService {
    private final BookingRepository bookingRepo;
    private final UserRepository userRepo;
    private final ParkingSlotRepository slotRepo;

    public BookingService(BookingRepository b,UserRepository u,ParkingSlotRepository s){bookingRepo=b;userRepo=u;slotRepo=s;}

    public Booking create(Long userId,Long slotId,Booking b){
        User user=userRepo.findById(userId).orElseThrow(()->new RuntimeException("User not found"));
        ParkingSlot slot=slotRepo.findById(slotId).orElseThrow(()->new RuntimeException("Slot not found"));
        if(!"AVAILABLE".equalsIgnoreCase(slot.getStatus())) throw new RuntimeException("Slot is unavailable");
        String registeredVehicle=normalizeVehicleNumber(user.getVehicleNumber());
        String bookingVehicle=normalizeVehicleNumber(b.getVehicleNumber());
        if(registeredVehicle.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"No vehicle number is saved to your account. Please update your registered vehicle number before booking.");
        if(bookingVehicle.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Please enter your registered vehicle number.");
        if(!registeredVehicle.equals(bookingVehicle)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Vehicle number does not match your registration. Booking cannot continue.");
        // Store the account's canonical vehicle number after validating the user's entry.
        b.setVehicleNumber(user.getVehicleNumber().trim().toUpperCase());
        if(b.getBookingDate()==null||b.getStartTime()==null||b.getEndTime()==null) throw new RuntimeException("Date and time are required");
        if(!b.getEndTime().isAfter(b.getStartTime())) throw new RuntimeException("End time must be after start time");

        List<Booking> old=bookingRepo.findByBookingDateAndSlotSlotIdAndBookingStatus(b.getBookingDate(),slotId,"CONFIRMED");
        for(Booking x:old){
            LocalTime s=x.getStartTime(), e=x.getEndTime();
            if(b.getStartTime().isBefore(e)&&b.getEndTime().isAfter(s)) throw new RuntimeException("Slot already booked for this time");
        }
        b.setUser(user); b.setSlot(slot);
        if(b.getAmount()==null)b.setAmount(50.0);
        if(b.getPaymentStatus()==null)b.setPaymentStatus("PENDING");
        if(b.getBookingStatus()==null)b.setBookingStatus("CONFIRMED");
        return bookingRepo.save(b);
    }
    private String normalizeVehicleNumber(String value){
        return value==null?"":value.toUpperCase().replaceAll("[^A-Z0-9]","");
    }
    public List<Booking> all(){return bookingRepo.findAll();}
    public Booking byId(Long id){return bookingRepo.findById(id).orElseThrow(()->new RuntimeException("Booking not found"));}
    public List<Booking> byUser(Long id){return bookingRepo.findByUserUserId(id);}
    public Booking update(Long id,Booking d){Booking b=byId(id);b.setPaymentMethod(d.getPaymentMethod());b.setPaymentStatus(d.getPaymentStatus());b.setTransactionId(d.getTransactionId());b.setBookingStatus(d.getBookingStatus());return bookingRepo.save(b);}
    public void cancel(Long id){Booking b=byId(id);b.setBookingStatus("CANCELLED");bookingRepo.save(b);}
}
