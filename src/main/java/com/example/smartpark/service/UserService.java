package com.example.smartpark.service;
import com.example.smartpark.entity.User;
import com.example.smartpark.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    private final UserRepository repo;
    public UserService(UserRepository repo){this.repo=repo;}

    public User register(User u){
        if(repo.findByEmail(u.getEmail()).isPresent()) throw new RuntimeException("Email already registered");
        u.setRole("USER"); u.setStatus("ACTIVE"); return repo.save(u);
    }
    public User login(String email,String password){
        User u=repo.findByEmail(email).orElseThrow(()->new RuntimeException("Invalid email or password"));
        if(!u.getPassword().equals(password)) throw new RuntimeException("Invalid email or password");
        if(!"ACTIVE".equalsIgnoreCase(u.getStatus())) throw new RuntimeException("Account is inactive");
        return u;
    }
    public List<User> all(){return repo.findAll();}
    public User byId(Long id){return repo.findById(id).orElseThrow(()->new RuntimeException("User not found"));}
    public User update(Long id,User d){
        User u=byId(id); u.setName(d.getName()); u.setMobile(d.getMobile()); u.setVehicleNumber(d.getVehicleNumber()); u.setVehicleType(d.getVehicleType()); u.setStatus(d.getStatus()); return repo.save(u);
    }
    public void delete(Long id){repo.deleteById(id);}
}
