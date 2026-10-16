package com.example.smartpark.dto;
public class RegisterRequest {
    private String name,email,password,mobile,vehicleNumber,vehicleType;
    public RegisterRequest(){}
    public String getName(){return name;} public void setName(String v){name=v;}
    public String getEmail(){return email;} public void setEmail(String v){email=v;}
    public String getPassword(){return password;} public void setPassword(String v){password=v;}
    public String getMobile(){return mobile;} public void setMobile(String v){mobile=v;}
    public String getVehicleNumber(){return vehicleNumber;} public void setVehicleNumber(String v){vehicleNumber=v;}
    public String getVehicleType(){return vehicleType;} public void setVehicleType(String v){vehicleType=v;}
}
