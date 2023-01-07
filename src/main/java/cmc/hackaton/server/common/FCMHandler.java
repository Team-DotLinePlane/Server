package cmc.hackaton.server.common;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import java.io.FileInputStream;
<<<<<<< HEAD
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
=======
import java.io.FileNotFoundException;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
>>>>>>> a723591 (feat: FCM 푸시 알림 베이스코드 작성)

@Slf4j
public class FCMHandler {
    private static FirebaseMessaging firebaseMessaging() throws IOException {
        GoogleCredentials googleCredentials = GoogleCredentials.fromStream(new FileInputStream("src/main/java/cmc/hackaton/server/config/google-services.json"));
        FirebaseOptions firebaseOptions = FirebaseOptions
            .builder()
            .setCredentials(googleCredentials)
            .setProjectId("209531163722")
            .build();
        FirebaseApp app = FirebaseApp.initializeApp(firebaseOptions, "my-app");
        return FirebaseMessaging.getInstance(app);
    }

    public static void notiMealTime(String token) {
        try {
            Message message = Message.builder()
                .putData("message", "meal")
                .setToken(token)
                .build();

            FirebaseMessaging firebaseMessaging = firebaseMessaging();
            firebaseMessaging.send(message);
        } catch (Exception msg) {
            log.debug(msg.getMessage());
        };
    }

    public static void notiVoteComplete(String token) {
        try {
            Message message = Message.builder()
                .putData("message", "vote")
                .setToken(token)
                .build();

            FirebaseMessaging firebaseMessaging = firebaseMessaging();
            firebaseMessaging.send(message);
        } catch (Exception msg) {
            log.debug(msg.getMessage());
        };
    }
}
