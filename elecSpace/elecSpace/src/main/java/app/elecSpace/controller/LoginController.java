package app.elecSpace.controller;

import app.elecSpace.entity.User;
import app.elecSpace.enums.Role;
import app.elecSpace.repository.UserRepo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private final UserRepo userRepo;
    PasswordEncoder passwordEncoder;

    public LoginController(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/me")
    public ResponseEntity<?> currentUser(Authentication authentication) {
        if (authentication == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        String email = authentication.getName();

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return ResponseEntity.ok(user);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Map<String, Object> payload) {
        String email = (String) payload.get("email");
        if (userRepo.findByEmail(email).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
        }
        User user = new User();
        user.setName((String) payload.get("name"));
        user.setEmail(email);
        user.setUsername((String) payload.get("username"));
        user.setPassword(passwordEncoder.encode((String) payload.get("password")));
        user.setImgUrl((String) payload.get("profilePic"));
        user.setIdCardNumber(((Number) payload.get("idCardNumber")).longValue());
        String roleStr = (String) payload.get("role");
        user.setRole(Role.valueOf((roleStr == null || roleStr.isEmpty()) ? "EMPLOYEE" : roleStr));

        userRepo.save(user);
        return ResponseEntity.ok(user);
    }
}
