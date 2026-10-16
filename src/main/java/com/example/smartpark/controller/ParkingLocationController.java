package com.example.smartpark.controller;
import com.example.smartpark.entity.ParkingLocation;
import com.example.smartpark.service.ParkingLocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/locations") @CrossOrigin(origins="*")
public class ParkingLocationController {
    private final ParkingLocationService service;
    public ParkingLocationController(ParkingLocationService s){service=s;}
    @PostMapping public ResponseEntity<ParkingLocation> create(@RequestBody ParkingLocation x){return ResponseEntity.ok(service.save(x));}
    @GetMapping public ResponseEntity<List<ParkingLocation>> all(){return ResponseEntity.ok(service.all());}
    @GetMapping("/{id}") public ResponseEntity<ParkingLocation> byId(@PathVariable Long id){return ResponseEntity.ok(service.byId(id));}
    @PutMapping("/{id}") public ResponseEntity<ParkingLocation> update(@PathVariable Long id,@RequestBody ParkingLocation x){return ResponseEntity.ok(service.update(id,x));}
    @DeleteMapping("/{id}") public ResponseEntity<String> delete(@PathVariable Long id){service.delete(id);return ResponseEntity.ok("Location deleted");}
}
