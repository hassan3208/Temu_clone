package com.hassan.temu_clone_2;

import android.content.Context;
import android.util.Log;

import com.google.api.client.util.Lists;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.dialogflow.v2.*;
import com.google.protobuf.Struct;
import com.google.protobuf.Value;

import java.io.IOException;
import java.util.UUID;

public class DialogflowBot {
    private SessionsClient sessionsClient;
    private SessionName session;

    public DialogflowBot(Context context, String projectId) {
        try {
            GoogleCredentials credentials = GoogleCredentials
                    .fromStream(context.getResources().openRawResource(R.raw.credentials))
                    .createScoped(Lists.newArrayList());

            sessionsClient = SessionsClient.create(
                    SessionsSettings.newBuilder()
                            .setCredentialsProvider(FixedCredentialsProvider.create(credentials))
                            .build()
            );

            session = SessionName.of(projectId, UUID.randomUUID().toString());
            Log.d("DialogflowBot", "Session initialized: " + session);
        } catch (IOException e) {
            Log.e("DialogflowBot", "Error loading credentials: " + e.getMessage());
        } catch (Exception e) {
            Log.e("DialogflowBot", "Unexpected error initializing DialogflowBot: " + e.getMessage());
        }
    }

    public String sendMessage(String message) {
        try {
            if (sessionsClient == null || session == null) {
                Log.e("DialogflowBot", "Session or SessionsClient is not initialized.");
                return "Error: DialogflowBot is not initialized.";
            }

            TextInput textInput = TextInput.newBuilder()
                    .setText(message)
                    .setLanguageCode("en-US")
                    .build();
            QueryInput queryInput = QueryInput.newBuilder().setText(textInput).build();

            DetectIntentRequest request = DetectIntentRequest.newBuilder()
                    .setSession(session.toString())
                    .setQueryInput(queryInput)
                    .build();

            DetectIntentResponse response = sessionsClient.detectIntent(request);
            return response.getQueryResult().getFulfillmentText();
        } catch (Exception e) {
            Log.e("DialogflowBot", "Error in sendMessage: " + e.getMessage());
            return "Error: " + e.getMessage();
        }
    }
}
