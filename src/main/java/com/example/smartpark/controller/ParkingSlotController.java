package com.example.smartpark.controller;
import com.example.smartpark.entity.ParkingSlot;
import com.example.smartpark.service.ParkingSlotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/slots") @CrossOrigin(origins="*")
public class ParkingSlotController {
    private final ParkingSlotService service;
    public ParkingSlotController(ParkingSlotService s){service=s;}
    @PostMapping("/location/{locationId}") public ResponseEntity<ParkingSlot> create(@PathVariable Long locationId,@RequestBody ParkingSlot x){return ResponseEntity.ok(service.save(locationId,x));}
    @GetMapping public ResponseEntity<List<ParkingSlot>> all(){return ResponseEntity.ok(service.all());}
    @GetMapping("/location/{locationId}") public ResponseEntity<List<ParkingSlot>> byLocation(@PathVariable Long locationId){return ResponseEntity.ok(service.byLocation(locationId));}
    @GetMapping("/available") public ResponseEntity<List<ParkingSlot>> available(){return ResponseEntity.ok(service.available());}
    @GetMapping("/{id}") public ResponseEntity<ParkingSlot> byId(@PathVariable Long id){return ResponseEntity.ok(service.byId(id));}
    @PutMapping("/{id}") public ResponseEntity<ParkingSlot> update(@PathVariable Long id,@RequestBody ParkingSlot x){return ResponseEntity.ok(service.update(id,x));}
    @DeleteMapping("/{id}") public ResponseEntity<String> delete(@PathVariable Long id){service.delete(id);return ResponseEntity.ok("Slot deleted");}
}
