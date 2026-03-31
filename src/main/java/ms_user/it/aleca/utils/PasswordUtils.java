package ms_user.it.aleca.utils;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordUtils {

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



}
