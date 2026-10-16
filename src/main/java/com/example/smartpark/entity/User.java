package com.example.smartpark.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable=false) private String name;
    @Column(nullable=false, unique=true) private String email;
    @Column(nullable=false) private String password;
    private String mobile;
    private String vehicleNumber;
    private String vehicleType;
    @Column(nullable=false) private String role = "USER";
    @Column(nullable=false) private String status = "ACTIVE";

    public User() {}

    public Long getUserId(){return userId;}
    public void setUserId(Long v){userId=v;}
    public String getName(){return name;}
    public void setName(String v){name=v;}
    public String getEmail(){return email;}
    public void setEmail(String v){email=v;}
    public String getPassword(){return password;}
    public void setPassword(String v){password=v;}
    public String getMobile(){return mobile;}
    public void setMobile(String v){mobile=v;}
    public String getVehicleNumber(){return vehicleNumber;}
    public void setVehicleNumber(String v){vehicleNumber=v;}
    public String getVehicleType(){return vehicleType;}
    public void setVehicleType(String v){vehicleType=v;}
    public String getRole(){return role;}
    public void setRole(String v){role=v;}
    public String getStatus(){return status;}
    public void setStatus(String v){status=v;}
}
