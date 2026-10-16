# SmartPark — Smart Parking Management System

Complete college full-stack Java project using Java 17, Spring Boot, Spring Data JPA, MySQL, HTML, CSS and JavaScript.

## Main website
Home • About • Locations • Find Parking • Contact • Login • Register

## User flow
Register/Login → Dashboard → Find Parking → Select Location → Select Slot → Booking Details → Demo Payment → Booking Confirmation → My Bookings / Profile

## Admin flow
Admin Login → Dashboard → Manage Locations → Manage Slots → Manage Users → Manage Bookings → Reports

## Important
- Software-based project. No IoT sensors, RFID, Arduino, Raspberry Pi or cameras.
- Payment is a demo/testing flow only. No real money is processed.
- Parking locations include Google Maps URLs; no Maps API key is required.

## Demo admin login
Email: `admin@smartpark.com`
Password: `admin123`

The application creates this demo admin automatically if it does not already exist.

## Database
Create:
```sql
CREATE DATABASE smartpark_db;
```
Then set your own MySQL password in `src/main/resources/application.properties`:
```properties
spring.datasource.password=YOUR_MYSQL_PASSWORD
```

## Run in VS Code PowerShell
```powershell
.\mvnw.cmd spring-boot:run
```
Open `http://localhost:8080/`

On the first successful DB connection, three demo parking locations and their slots are created if the location table is empty.

## Parking locations data
The startup seeds a broad set of real public parking locations for Mumbai and Navi Mumbai, with a Google Maps search link for each location. The location list is based on publicly available BMC/NMMC parking information and current parking directories; Google Maps itself remains dynamic, so the site also provides an "Explore Parking on Google Maps" link for live results.

## SmartPark UI updates
- Parking locations now include a visual parking-lot preview.
- Slot selection uses a parking-bay board with available and booked states.
- Checkout is titled **Payment Options** and includes UPI, a demo QR, card fields with demo OTP, and Pay at Parking.
- Choosing **Pay at Parking** records a pending payment and displays the pay-at-parking instruction on the confirmation page.

**Payment safety note:** this is a college-project simulation, not a payment gateway. Do not enter real card information. For the simulated card flow, use the displayed demo OTP `123456`; card details are checked in the browser and are not sent to the server. The QR code is marked as demo-only and does not initiate a transfer.


### Latest visual updates
- Location cards now show parking photography, with mall-focused imagery used for mall/shopping-centre locations and a local SmartPark illustration as a fallback.
- The slot map starts with a mix of available and demo-occupied bays; selecting a booked bay is disabled. Existing databases seeded by an older version are updated only when every slot is still marked available.
