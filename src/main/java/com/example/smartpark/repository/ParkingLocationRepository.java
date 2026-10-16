package com.example.smartpark.repository;
import com.example.smartpark.entity.ParkingLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingLocationRepository extends JpaRepository<ParkingLocation,Long>{}
