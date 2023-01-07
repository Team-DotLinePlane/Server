package cmc.hackaton.server.common;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

public class FCMHandler {

    FirebaseMessaging firebaseMessaging() throws IOException {
        GoogleCredentials googleCredentials = GoogleCredentials
            .fromStream(new FileInputStream("/home/hgdkim2/Desktop/Server/src/main/java/cmc/hackaton/server/config/google-services.json"));
        FirebaseOptions firebaseOptions = FirebaseOptions
            .builder()
            .setCredentials(googleCredentials)
            .setProjectId("209531163722")
            .build();
        FirebaseApp app = FirebaseApp.initializeApp(firebaseOptions, "my-app");
        return FirebaseMessaging.getInstance(app);
    }

    public void init() throws IOException, FirebaseMessagingException {
//        FileInputStream serviceAccount =
//            new FileInputStream("/home/hgdkim2/Desktop/Server/src/main/java/cmc/hackaton/server/config/google-services.json");
//
//        FirebaseOptions options = new FirebaseOptions.Builder()
//            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//            .build();
//
//        FirebaseApp.initializeApp(options);

        // This registration token comes from the client FCM SDKs.
//        String registrationToken = "e6DAgWdBRnW_-LkACoyEMf:APA91bFAUnat8KayRMpbYRi9xhPhUXE_Hdh8efd-1B9e3v8KGn3JguR5oi4ACJ7-gGWfQ4pdsGfX2vXeqDdWgc2D_aq3gVd919i7YuAYqsx8xrDosVD1STs1ly79gIPSx2UK7nrU1qn3";
//
//        FirebaseMessaging firebaseMessaging = firebaseMessaging();
//
//        Message message = Message.builder()
//            .putData("score", "850")
//            .putData("time", "2:45")
//            .setToken(registrationToken)
//            .build();
//
//        String response = firebaseMessaging.send(message);
//        System.out.println("Successfully sent message: " + response);

        // See documentation on defining a message payload.


        // Send a message to the device corresponding to the provided
        // registration token.

//        Notification notification = Notification
//            .builder()
//            .setTitle("TEST")
//            .setBody("TEST2")
//            .build();
//
//        Message message = Message
//            .builder()
//            .set
//            .setToken(registrationToken)
//            .build();

        // Response is a message ID string.
    }


}
