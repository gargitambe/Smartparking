package com.example.smartpark.service;
import com.example.smartpark.entity.*;
import com.example.smartpark.repository.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ParkingSlotService {
    private final ParkingSlotRepository slotRepo;
    private final ParkingLocationRepository locationRepo;
    public ParkingSlotService(ParkingSlotRepository s,ParkingLocationRepository l){slotRepo=s;locationRepo=l;}

    public ParkingSlot save(Long locationId,ParkingSlot slot){
        ParkingLocation loc=locationRepo.findById(locationId).orElseThrow(()->new RuntimeException("Location not found"));
        slot.setLocation(loc); if(slot.getStatus()==null)slot.setStatus("AVAILABLE"); return slotRepo.save(slot);
    }
    public List<ParkingSlot> all(){return slotRepo.findAll();}
    public List<ParkingSlot> byLocation(Long id){return slotRepo.findByLocationLocationId(id);}
    public List<ParkingSlot> available(){return slotRepo.findByStatus("AVAILABLE");}
    public ParkingSlot byId(Long id){return slotRepo.findById(id).orElseThrow(()->new RuntimeException("Slot not found"));}
    public ParkingSlot update(Long id,ParkingSlot d){ParkingSlot x=byId(id);x.setSlotNumber(d.getSlotNumber());x.setSlotType(d.getSlotType());x.setStatus(d.getStatus());return slotRepo.save(x);}
    public void delete(Long id){slotRepo.deleteById(id);}
}
