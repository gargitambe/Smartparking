package com.example.smartpark.service;
import com.example.smartpark.entity.ParkingLocation;
import com.example.smartpark.repository.ParkingLocationRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ParkingLocationService {
    private final ParkingLocationRepository repo;
    public ParkingLocationService(ParkingLocationRepository repo){this.repo=repo;}
    public ParkingLocation save(ParkingLocation x){if(x.getStatus()==null)x.setStatus("ACTIVE");return repo.save(x);}
    public List<ParkingLocation> all(){return repo.findAll();}
    public ParkingLocation byId(Long id){return repo.findById(id).orElseThrow(()->new RuntimeException("Location not found"));}
    public ParkingLocation update(Long id,ParkingLocation d){
        ParkingLocation x=byId(id); x.setLocationName(d.getLocationName()); x.setAddress(d.getAddress()); x.setCity(d.getCity()); x.setTotalSlots(d.getTotalSlots()); x.setDescription(d.getDescription()); x.setGoogleMapsUrl(d.getGoogleMapsUrl()); x.setStatus(d.getStatus()); return repo.save(x);
    }
    public void delete(Long id){repo.deleteById(id);}
}
