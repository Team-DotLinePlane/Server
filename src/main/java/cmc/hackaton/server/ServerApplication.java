package cmc.hackaton.server;

import cmc.hackaton.server.common.FCMHandler;
import com.google.firebase.messaging.FirebaseMessagingException;
import java.io.IOException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);

        FCMHandler fcmHandler = new FCMHandler();
        try {
            fcmHandler.init();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (FirebaseMessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
