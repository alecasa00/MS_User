package ms_user.it.aleca.utils;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;

public class PasswordUtils {

    private static final String CHARSET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%";
    private static final int LENGTH = 12;
    private static final SecureRandom RANDOM = new SecureRandom();


    public static String passwordEncoder(String password){

        /*Consente la validazione delle password con formato moderno e legacy
        * consente di aggiornare l'encoding in futuro
        * */
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        return passwordEncoder.encode(password);
    }

    public static boolean checkPassword(String password, String encodedPassword){

        /*Consente la validazione delle password con formato moderno e legacy
         * consente di aggiornare l'encoding in futuro
         * */
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        return passwordEncoder.matches(password , encodedPassword);
    }

    public static String generateRandomPassword(){
        StringBuilder sb = new StringBuilder(LENGTH);
        for (int i = 0; i < LENGTH; i++) {
            sb.append(CHARSET.charAt(RANDOM.nextInt(CHARSET.length())));
        }
        return sb.toString();
    }



}
