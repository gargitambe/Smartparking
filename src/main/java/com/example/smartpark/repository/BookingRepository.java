package com.example.smartpark.repository;
import com.example.smartpark.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Long>{
    List<Booking> findByUserUserId(Long userId);
    List<Booking> findByBookingDateAndSlotSlotIdAndBookingStatus(LocalDate date,Long slotId,String status);
}
