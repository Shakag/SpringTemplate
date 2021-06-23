import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

public class T {
    public static void main(String[] args) {
        SecureRandom secureRandom = new SecureRandom("ctcgO1DPh0/L9/K2bpNHNu".getBytes(StandardCharsets.UTF_8));
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2A,12,secureRandom);
        String encode = bCryptPasswordEncoder.encode("123");

//        boolean matches = bCryptPasswordEncoder.matches("123", "$2a$12$ctcgO1DPh0/L9/K2bpNHNuRZhFe3o2cbFtP20.9LJV4aQewlfBN3y");
//        System.out.println(encode);
//        System.out.println(matches);
    }
}
