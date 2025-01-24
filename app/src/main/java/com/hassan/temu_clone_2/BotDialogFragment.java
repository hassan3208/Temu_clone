package com.hassan.temu_clone_2;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BotDialogFragment extends DialogFragment {

    private DialogflowBot dialogflowBot;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bot_dialog, container, false);

        EditText userInput = view.findViewById(R.id.editTextUserInput);
        Button sendButton = view.findViewById(R.id.buttonSend);
        TextView botResponse = view.findViewById(R.id.textViewBotResponse);

        dialogflowBot = new DialogflowBot(getContext(), "temu-clone"); // Replace with your project ID

        sendButton.setOnClickListener(v -> {
            String message = userInput.getText().toString();
            if (!message.isEmpty()) {
                String response = dialogflowBot.sendMessage(message);
                botResponse.setText(response);
            }
        });

        return view;
    }
}
