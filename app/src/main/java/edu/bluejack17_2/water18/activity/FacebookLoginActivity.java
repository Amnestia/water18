package edu.bluejack17_2.water18.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;

import edu.bluejack17_2.water18.R;

public class FacebookLoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private CallbackManager callbackManager;

    TextView email;
    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_login);

        FacebookSdk.sdkInitialize(this);
        callbackManager = CallbackManager.Factory.create();
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email);
        name = findViewById(R.id.name);
        LoginButton loginButton = (LoginButton) findViewById(R.id.login_btn);

        loginButton.setReadPermissions(Arrays.asList("email"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
//                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                email.setText(loginResult.getAccessToken().getToken().toString());
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Toast.makeText(FacebookLoginActivity.this, "User has Canceled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        loginButton.performClick();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void handleFacebookAccessToken(AccessToken token) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else{
                            Toast.makeText(FacebookLoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });


//        mAuth.signInWithCredential(credential)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            updateUI(user);
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Toast.makeText(FacebookLoginActivity.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
////                            updateUI(null);
//                        }
//
//                        // ...
//                    }
//                });
    }

    private void updateUI(final FirebaseUser fuser) {
//        hideProgressDialog();
        if (fuser != null) {

            email.setText(fuser.getEmail());
            name.setText(fuser.getDisplayName());

            DatabaseReference r = FirebaseDatabase.getInstance().getReference("user");
            r.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    //search the database for the user with this id
                    for(DataSnapshot data: dataSnapshot.getChildren()){
                        //if found go to customer activity
                        if(data.child("id").getValue().toString().equals(fuser.getUid())){
//                            Toast.makeText(GoogleLoginActivity.this, "User Found", Toast.LENGTH_SHORT).show();
                            Intent home = new Intent(FacebookLoginActivity.this, CustomerMainActivity.class);
                            FacebookLoginActivity.this.startActivity(home);
                            break;
                        }

                        //Log.e("TESTTTT", data.getValue().toString());
                        //Class user
                        //User u = data.getValue(User.class);
                    }
                    //if not found, go to edit profile activity

                    //debug
                    Intent home = new Intent(FacebookLoginActivity.this, CustomerMainActivity.class);
                    FacebookLoginActivity.this.startActivity(home);
                    //end of debug

//                    Intent editProfile = new Intent(FacebookLoginActivity.this, EditProfileActivity.class);
//                    FacebookLoginActivity.this.startActivity(editProfile);
                    Toast.makeText(FacebookLoginActivity.this, "There is no user of this id", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

//            mStatusTextView.setText(getString(R.string.facebook_status_fmt, user.getDisplayName()));
//            mDetailTextView.setText(getString(R.string.firebase_status_fmt, user.getUid()));

//            findViewById(R.id.button_facebook_login).setVisibility(View.GONE);
//            findViewById(R.id.button_facebook_signout).setVisibility(View.VISIBLE);
        } else {
//            mStatusTextView.setText(R.string.signed_out);
//            mDetailTextView.setText(null);
//
//            findViewById(R.id.button_facebook_login).setVisibility(View.VISIBLE);
//            findViewById(R.id.button_facebook_signout).setVisibility(View.GONE);
        }
    }

    private void signOutFacebook(){
        mAuth.signOut();
        LoginManager.getInstance().logOut();

        updateUI(null);

    }

}
