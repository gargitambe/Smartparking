package com.example.smartpark.controller;
import com.example.smartpark.entity.Booking;
import com.example.smartpark.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/bookings") @CrossOrigin(origins="*")
public class BookingController {
    private final BookingService service;
    public BookingController(BookingService s){service=s;}
    @PostMapping("/user/{userId}/slot/{slotId}") public ResponseEntity<Booking> create(@PathVariable Long userId,@PathVariable Long slotId,@RequestBody Booking b){return ResponseEntity.ok(service.create(userId,slotId,b));}
    @GetMapping public ResponseEntity<List<Booking>> all(){return ResponseEntity.ok(service.all());}
    @GetMapping("/{id}") public ResponseEntity<Booking> byId(@PathVariable Long id){return ResponseEntity.ok(service.byId(id));}
    @GetMapping("/user/{userId}") public ResponseEntity<List<Booking>> byUser(@PathVariable Long userId){return ResponseEntity.ok(service.byUser(userId));}
    @PutMapping("/{id}") public ResponseEntity<Booking> update(@PathVariable Long id,@RequestBody Booking b){return ResponseEntity.ok(service.update(id,b));}
    @DeleteMapping("/{id}") public ResponseEntity<String> cancel(@PathVariable Long id){service.cancel(id);return ResponseEntity.ok("Booking cancelled");}
}
