package com.example.smartpark.controller;
import com.example.smartpark.entity.User;
import com.example.smartpark.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/users") @CrossOrigin(origins="*")
public class UserController {
    private final UserService service;
    public UserController(UserService s){service=s;}

    @PostMapping("/register") public ResponseEntity<User> register(@RequestBody User u){return ResponseEntity.ok(service.register(u));}
    @PostMapping("/login") public ResponseEntity<User> login(@RequestBody LoginRequest r){return ResponseEntity.ok(service.login(r.email,r.password));}
    @GetMapping public ResponseEntity<List<User>> all(){return ResponseEntity.ok(service.all());}
    @GetMapping("/{id}") public ResponseEntity<User> byId(@PathVariable Long id){return ResponseEntity.ok(service.byId(id));}
    @PutMapping("/{id}") public ResponseEntity<User> update(@PathVariable Long id,@RequestBody User u){return ResponseEntity.ok(service.update(id,u));}
    @DeleteMapping("/{id}") public ResponseEntity<String> delete(@PathVariable Long id){service.delete(id);return ResponseEntity.ok("User deleted");}

    public static class LoginRequest { public String email; public String password; }
}
