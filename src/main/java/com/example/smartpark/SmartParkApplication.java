package com.example.smartpark;

import com.example.smartpark.entity.ParkingLocation;
import com.example.smartpark.entity.ParkingSlot;
import com.example.smartpark.repository.ParkingLocationRepository;
import com.example.smartpark.repository.ParkingSlotRepository;
import com.example.smartpark.repository.UserRepository;
import com.example.smartpark.entity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SmartParkApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmartParkApplication.class, args);
    }

    @Bean
    CommandLineRunner seedDemoParking(ParkingLocationRepository locationRepository,
                                       ParkingSlotRepository slotRepository,
                                       UserRepository userRepository) {
        return args -> {
            if (userRepository.findByEmail("admin@smartpark.com").isEmpty()) {
                User admin = new User();
                admin.setName("SmartPark Admin");
                admin.setEmail("admin@smartpark.com");
                admin.setPassword("admin123");
                admin.setMobile("9999999999");
                admin.setRole("ADMIN");
                admin.setStatus("ACTIVE");
                userRepository.save(admin);
            }

            // Seed real public parking locations so the website has useful data immediately.
            // Existing records are kept; missing locations are added by name.
            String[][] locations = {
                {"Near Artesia Building, Lower Parel", "Near Artesia Building, Lower Parel, Mumbai", "Mumbai", "BMC-authorised public parking location.", "Near Artesia Building, Lower Parel, Mumbai"},
                {"Altamount Road, Kemps Corner", "Altamount Road, Kemps Corner, Mumbai", "Mumbai", "Public parking location in South Mumbai.", "Altamount Road, Kemps Corner, Mumbai"},
                {"Near Elco Market, Bandra", "Near Elco Market, Bandra West, Mumbai", "Mumbai", "Public parking location near Elco Market.", "Elco Market, Bandra West, Mumbai"},
                {"BMC Parking, Kurla West", "Kurla West, Mumbai", "Mumbai", "BMC parking location in Kurla West.", "BMC Parking, Kurla West, Mumbai"},
                {"Near Boomerang Building, Kurla West", "Near Boomerang Building, Kurla West, Mumbai", "Mumbai", "Public parking location near Boomerang Building.", "Boomerang Building, Kurla West, Mumbai"},
                {"Topiwala Market Building, Kurla", "Topiwala Market Building, Kurla West, Mumbai", "Mumbai", "Public parking location near Topiwala Market.", "Topiwala Market Building, Kurla West, Mumbai"},
                {"One Indiabulls, Lower Parel", "One Indiabulls Centre, Lower Parel, Mumbai", "Mumbai", "Parking location near One Indiabulls.", "One Indiabulls Centre, Lower Parel, Mumbai"},
                {"Cygnus Tower, Santacruz East", "Cygnus Tower, Santacruz East, Mumbai", "Mumbai", "Public parking location near Cygnus Tower.", "Cygnus Tower, Santacruz East, Mumbai"},
                {"Lodha Excelus, NM Joshi Marg", "Lodha Excelus, NM Joshi Marg, Mumbai", "Mumbai", "Parking location near Lodha Excelus.", "Lodha Excelus, NM Joshi Marg, Mumbai"},
                {"Runwal Green, Nahur", "Runwal Green, GMLR, Nahur, Mumbai", "Mumbai", "Public parking location near Runwal Green.", "Runwal Green, Nahur, Mumbai"},
                {"Kalpataru Avana, Parel-Sewri", "Kalpataru Avana, Parel-Sewri, Mumbai", "Mumbai", "Parking location near Kalpataru Avana.", "Kalpataru Avana, Parel Sewri, Mumbai"},
                {"Insignia Building, Santacruz East", "Insignia Building, Santacruz East, Mumbai", "Mumbai", "Public parking location near Insignia Building.", "Insignia Building, Santacruz East, Mumbai"},
                {"Runwal Anthurium, Mulund West", "Runwal Anthurium, Mulund West, Mumbai", "Mumbai", "Parking location near Runwal Anthurium.", "Runwal Anthurium, Mulund West, Mumbai"},
                {"Lodha Supreme, Kanjurmarg", "Lodha Supreme, Kanjurmarg, Mumbai", "Mumbai", "Parking location near Lodha Supreme.", "Lodha Supreme, Kanjurmarg, Mumbai"},
                {"Runwal Building, Nepean Sea Road", "Nepean Sea Road, Mumbai", "Mumbai", "Public parking location near Runwal Building.", "Runwal Building, Nepean Sea Road, Mumbai"},
                {"Behind Umiya Mata Temple, Goregaon East", "Behind Umiya Mata Temple, Goregaon East, Mumbai", "Mumbai", "Public parking location in Goregaon East.", "Umiya Mata Temple, Goregaon East, Mumbai"},
                {"Near Oshiwara Metro Station, Andheri West", "Oshiwara Metro Station, Andheri West, Mumbai", "Mumbai", "Parking location near Oshiwara Metro Station.", "Oshiwara Metro Station, Andheri West, Mumbai"},
                {"Club Aquaria, Borivali West", "Club Aquaria, Borivali West, Mumbai", "Mumbai", "Parking location near Club Aquaria.", "Club Aquaria, Borivali West, Mumbai"},
                {"Celestia Building, Parel-Sewri", "Celestia Building, Parel-Sewri, Mumbai", "Mumbai", "Parking location near Celestia Building.", "Celestia Building, Parel Sewri, Mumbai"},
                {"Runwal Building, Andheri West", "Andheri West, Mumbai", "Mumbai", "Free/public parking listing in Andheri West.", "Runwal Building, Andheri West, Mumbai"},
                {"Near R City Mall, Vikhroli West", "R City Mall, Vikhroli West, Mumbai", "Mumbai", "Parking location near R City Mall.", "R City Mall, Vikhroli West, Mumbai"},
                {"Near Hub Mall, Goregaon East", "Hub Mall, Goregaon East, Mumbai", "Mumbai", "Parking location near Hub Mall.", "Hub Mall, Goregaon East, Mumbai"},
                {"Next to Kamala Mills, SB Road", "Senapati Bapat Road, Lower Parel, Mumbai", "Mumbai", "Parking location near Kamala Mills.", "Kamala Mills, SB Road, Mumbai"},
                {"Indiabulls Finance Center, SB Road", "Indiabulls Finance Center, Senapati Bapat Road, Mumbai", "Mumbai", "Parking location near Indiabulls Finance Center.", "Indiabulls Finance Center, SB Road, Mumbai"},
                {"Vikas Palazzo, Mulund West", "Vikas Palazzo, Mulund West, Mumbai", "Mumbai", "Parking location near Vikas Palazzo.", "Vikas Palazzo, Mulund West, Mumbai"},
                {"World Tower, SB Road", "World Tower, Senapati Bapat Road, Mumbai", "Mumbai", "Parking location near World Tower.", "World Tower, SB Road, Mumbai"},
                {"Jagannath Sawant Marg, Dadar West", "Jagannath Sawant Marg, Dadar West, Mumbai", "Mumbai", "BMC model parking facility location announced in 2026.", "Jagannath Sawant Marg, Dadar West, Mumbai"},
                {"CBD Sector 10 Belapur Parking", "CBD Sector 10, Belapur, Navi Mumbai", "Navi Mumbai", "NMMC parking location near Kapas Bhavan and Park Hotel.", "CBD Sector 10 Belapur Parking, Navi Mumbai"},
                {"CBD Belapur Sector 2 Road Parking", "Sector 2 Road, CBD Belapur, Navi Mumbai", "Navi Mumbai", "NMMC parking location near Ambedkar Udyan.", "CBD Belapur Sector 2 Road Parking, Navi Mumbai"},
                {"Belapur Sector 3 Mahakali Chowk Parking", "Sector 3 Mahakali Chowk, CBD Belapur, Navi Mumbai", "Navi Mumbai", "NMMC parking route around Belapur Sector 3.", "Mahakali Chowk, CBD Belapur, Navi Mumbai"},
                {"Nerul-Juinagar Sector 25 Parking", "Sector 25, Nerul-Juinagar, Navi Mumbai", "Navi Mumbai", "NMMC parking location in Nerul-Juinagar.", "Nerul Juinagar Sector 25 Parking, Navi Mumbai"},
                {"Nerul Station West Parking", "Nerul Station West, Navi Mumbai", "Navi Mumbai", "Parking near Nerul railway station.", "Nerul Station West Parking, Navi Mumbai"},
                {"Nerul Railway Station Parking", "Nerul Railway Station, Navi Mumbai", "Navi Mumbai", "Parking near Nerul railway station.", "Nerul Railway Station Parking, Navi Mumbai"},
                {"Terna Hospital Parking, Nerul", "Terna Hospital, Nerul, Navi Mumbai", "Navi Mumbai", "Parking near Terna Hospital.", "Terna Hospital Parking, Nerul, Navi Mumbai"},
                {"NMMC Parking Plot, Sector 29 Nerul", "Sector 29, Nerul, Navi Mumbai", "Navi Mumbai", "NMMC parking plot in Nerul.", "NMMC Parking Plot Sector 29 Nerul, Navi Mumbai"},
                {"Parking Plot near Jagruteshwar Lake, Vashi", "Sector 8, Vashi, Navi Mumbai", "Navi Mumbai", "Parking plot near Jagruteshwar Lake.", "Parking Plot near Jagruteshwar Lake, Vashi, Navi Mumbai"},
                {"Vashi Railway Parking", "Vashi Railway Station, Sector 30, Navi Mumbai", "Navi Mumbai", "Parking near Vashi railway station.", "Vashi Railway Parking, Navi Mumbai"},
                {"CIDCO Parking near Vashi Railway", "Vashi Railway Station area, Navi Mumbai", "Navi Mumbai", "CIDCO parking near Vashi railway station.", "CIDCO Parking near Vashi Railway, Navi Mumbai"},
                {"CIDCO Pradarshan Kendra Pay & Park", "Vashi, Navi Mumbai", "Navi Mumbai", "CIDCO pay-and-park location near Pradarshan Kendra.", "CIDCO Pradarshan Kendra Pay and Park, Vashi, Navi Mumbai"},
                {"NMMC Parking Opposite Cafe Vihar, Vashi", "Vashi, Navi Mumbai", "Navi Mumbai", "NMMC parking location near Cafe Vihar.", "NMMC Parking Opposite Cafe Vihar, Vashi, Navi Mumbai"},
                {"NMMC Parking in front of UP Bhavan, Vashi", "Vashi, Navi Mumbai", "Navi Mumbai", "NMMC parking location near UP Bhavan.", "NMMC Parking in front of UP Bhavan, Vashi, Navi Mumbai"},
                {"NMMC Parking near Vardhaman Palace", "Vashi, Navi Mumbai", "Navi Mumbai", "NMMC parking location near Vardhaman Palace.", "NMMC Parking near Vardhaman Palace, Navi Mumbai"},
                {"CIDCO Pay & Park, Sanpada Railway Station A", "Sanpada Railway Station, Navi Mumbai", "Navi Mumbai", "CIDCO pay-and-park near Sanpada railway station.", "CIDCO Pay and Park Sanpada Railway Station, Navi Mumbai"},
                {"CIDCO Pay & Park, Sanpada Railway Station B", "Sanpada Railway Station, Navi Mumbai", "Navi Mumbai", "CIDCO pay-and-park near Sanpada railway station.", "CIDCO Pay and Park Sanpada Railway Station, Navi Mumbai"},
                {"MGM Hospital Parking, CBD Belapur", "CBD Belapur, Navi Mumbai", "Navi Mumbai", "Parking near MGM Hospital.", "MGM Hospital Parking, CBD Belapur, Navi Mumbai"},
                {"Prabhat Shopping Center Parking, CBD Belapur", "CBD Belapur, Navi Mumbai", "Navi Mumbai", "Parking near Prabhat Shopping Center.", "Prabhat Shopping Center Parking, CBD Belapur, Navi Mumbai"},
                {"Inorbit Mall Parking, Navi Mumbai", "Inorbit Mall, Vashi, Navi Mumbai", "Navi Mumbai", "Parking near Inorbit Mall Navi Mumbai.", "Inorbit Mall Parking, Vashi, Navi Mumbai"}
            };

            for (String[] item : locations) {
                ensureLocation(locationRepository, slotRepository, item[0], item[1], item[2], 12, item[3], item[4]);
            }
        };
    }

    private void ensureLocation(ParkingLocationRepository locationRepository,
                                ParkingSlotRepository slotRepository,
                                String name, String address, String city, int totalSlots,
                                String description, String mapsQuery) {
        ParkingLocation location = locationRepository.findAll().stream()
                .filter(existing -> name.equalsIgnoreCase(existing.getLocationName()))
                .findFirst().orElse(null);

        if (location == null) {
            location = new ParkingLocation();
            location.setLocationName(name);
            location.setAddress(address);
            location.setCity(city);
            location.setTotalSlots(totalSlots);
            location.setDescription(description);
            location.setGoogleMapsUrl("https://www.google.com/maps/search/?api=1&query=" + mapsQuery.replace(" ", "+"));
            location.setStatus("ACTIVE");
            location = locationRepository.save(location);
        }

        java.util.List<ParkingSlot> existingSlots = slotRepository.findByLocationLocationId(location.getLocationId());
        if (existingSlots.isEmpty()) {
            for (int i = 1; i <= totalSlots; i++) {
                ParkingSlot slot = new ParkingSlot();
                slot.setLocation(location);
                slot.setSlotNumber(city.substring(0, 1).toUpperCase() + i);
                slot.setSlotType(demoVehicleType(i));
                // Sample occupied bays make the demo slot map realistic on first launch.
                slot.setStatus(i % 5 == 0 || i == 7 ? "BOOKED" : "AVAILABLE");
                slotRepository.save(slot);
            }
        } else {
            // Upgrade older demo databases that marked every bay as a car space.
            boolean needsVehicleMix = existingSlots.stream().noneMatch(slot -> "SUV".equalsIgnoreCase(slot.getSlotType())
                    || "EV".equalsIgnoreCase(slot.getSlotType()));
            boolean allAvailable = existingSlots.stream().allMatch(slot -> "AVAILABLE".equalsIgnoreCase(slot.getStatus()));
            for (int i = 0; i < existingSlots.size(); i++) {
                ParkingSlot slot = existingSlots.get(i);
                if (needsVehicleMix) slot.setSlotType(demoVehicleType(i + 1));
                if (allAvailable && ((i + 1) % 5 == 0 || i + 1 == 7)) slot.setStatus("BOOKED");
                if (needsVehicleMix || (allAvailable && "BOOKED".equalsIgnoreCase(slot.getStatus()))) slotRepository.save(slot);
            }
        }
    }

    private String demoVehicleType(int slotNumber) {
        int position = (slotNumber - 1) % 12;
        if (position < 4) return "CAR";
        if (position < 7) return "BIKE";
        if (position < 10) return "SUV";
        return "EV";
    }
}
