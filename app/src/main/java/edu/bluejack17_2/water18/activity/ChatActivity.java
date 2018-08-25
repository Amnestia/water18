package edu.bluejack17_2.water18.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.FirebaseDatabase;

import edu.bluejack17_2.water18.R;
import edu.bluejack17_2.water18.model.ChatMessage;
import edu.bluejack17_2.water18.storage.TransactionStorage;
import edu.bluejack17_2.water18.storage.UserStorage;

public class ChatActivity extends AppCompatActivity {

    FirebaseListAdapter<ChatMessage> adapter;
    int SIGN_IN_REQUEST_CODE = 10;

    boolean checkPhone(ChatMessage model,String phoneA,String phoneB)
    {
        return (model.getSender().equals(phoneA) && model.getReceiver().equals(phoneB)) ||
                (model.getSender().equals(phoneB) && model.getReceiver().equals(phoneA));
    }

    void displayChatMessages(){
        ListView listOfMessages = findViewById(R.id.list_of_messages);

        adapter = new FirebaseListAdapter<ChatMessage>(this, ChatMessage.class,
                R.layout.message, FirebaseDatabase.getInstance().getReference("chat")) {
            @Override
            protected void populateView(View v, ChatMessage model, int position) {
                if(!checkPhone(model, TransactionStorage.INSTANCE.getTransaction().getUser().getPhoneNumber(),UserStorage.INSTANCE.getUsers().get(0).getPhoneNumber()))
                return;
                TextView messageText = v.findViewById(R.id.message_text);
                TextView messageUser = v.findViewById(R.id.message_user);
                TextView messageTime = v.findViewById(R.id.message_time);

                messageText.setText(model.getMessageText());
                messageUser.setText(model.getSender());
                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)", model.getMessageTime()));
            }
        };

        listOfMessages.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        /*if(FirebaseAuth.getInstance().getCurrentUser() == null) {
            // Start sign in/sign up activity
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .build(),
                    SIGN_IN_REQUEST_CODE
            );
        } else {
            // User is already signed in. Therefore, display
            // a welcome Toast
            Toast.makeText(this,
                    "Welcome " + FirebaseAuth.getInstance()
                            .getCurrentUser()
                            .getDisplayName(),
                    Toast.LENGTH_LONG)
                    .show();

            // Load chat room contents

        }*/
        displayChatMessages();
        FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText input = findViewById(R.id.input);
                String currUserPhoneNumber = UserStorage.INSTANCE.getUser().getPhoneNumber();
                String userPhoneNumber=TransactionStorage.INSTANCE.getTransaction().getUser().getPhoneNumber();
                String adminPhoneNumber=UserStorage.INSTANCE.getUsers().get(0).getPhoneNumber();
                FirebaseDatabase.getInstance()
                        .getReference("chat")
                        .push()
                        .setValue(new ChatMessage(input.getText().toString(),currUserPhoneNumber ,
                               currUserPhoneNumber.equals(adminPhoneNumber)?userPhoneNumber:adminPhoneNumber )
                        );
                input.setText("");
            }
        });
    }
}
