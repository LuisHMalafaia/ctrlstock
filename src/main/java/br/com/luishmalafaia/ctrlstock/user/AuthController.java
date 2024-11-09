package br.com.luishmalafaia.ctrlstock.user;

import br.com.luishmalafaia.ctrlstock.user.dto.LoginRequestDTO;
import br.com.luishmalafaia.ctrlstock.user.dto.LoginResponseDTO;
import br.com.luishmalafaia.ctrlstock.user.dto.RegisterRequestDTO;
import br.com.luishmalafaia.ctrlstock.util.JWTUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.luishmalafaia.ctrlstock.user.User;

@RestController
@RequestMapping("v1/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterRequestDTO body){
        if(this.userRepository.findByEmail(body.email()).isPresent())
            return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(body.password());
        User newUser = new User(null, body.name(), body.email(), encryptedPassword, body.role());

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginRequestDTO body){
        User user = this.userRepository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not Found"));

        if(passwordEncoder.matches(body.password(), user.getPassword())){
            String token = this.jwtUtil.generateToken(user);
            return ResponseEntity.ok(new LoginResponseDTO(token, 7200, "Bearer"));
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/test")
    public ResponseEntity test(){
        return ResponseEntity.ok().build();
    }
}
