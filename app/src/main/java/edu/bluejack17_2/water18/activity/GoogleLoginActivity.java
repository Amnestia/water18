package edu.bluejack17_2.water18.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import edu.bluejack17_2.water18.R;
import edu.bluejack17_2.water18.firebase.Firebase;
import edu.bluejack17_2.water18.model.User;

public class GoogleLoginActivity extends AppCompatActivity{

    private static final int RC_SIGN_IN = 9001;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    TextView mStatusTextView;
    TextView mDetailTextView;
    Button btn_signout;

    private void signIn() {

        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_login);

        mStatusTextView = findViewById(R.id.email);
        mDetailTextView = findViewById(R.id.uid);
        btn_signout = findViewById(R.id.btnSignOut);

        btn_signout.setVisibility(View.INVISIBLE);
        btn_signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOutGoogle();
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        mAuth = FirebaseAuth.getInstance();
        signIn();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try{
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            }
            catch(ApiException e){

            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            updateUI(null);
                        }
                        // ...
                    }
                });
    }

    private void updateUI(final FirebaseUser fuser) {
//        hideProgressDialog();
        if (fuser != null) {
            mStatusTextView.setText(fuser.getEmail());
            mDetailTextView.setText(fuser.getUid());

            DatabaseReference r = FirebaseDatabase.getInstance().getReference("user");
            r.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    //search the database for the user with this id
                    for(DataSnapshot data: dataSnapshot.getChildren()){
                        //if found go to customer activity
                        if(data.child("id").getValue().toString().equals(fuser.getUid())){
//                            Toast.makeText(GoogleLoginActivity.this, "User Found", Toast.LENGTH_SHORT).show();
                            Intent home = new Intent(GoogleLoginActivity.this, CustomerMainActivity.class);
                            GoogleLoginActivity.this.startActivity(home);
                            break;
                        }

                        //Log.e("TESTTTT", data.getValue().toString());
                        //Class user
                        //User u = data.getValue(User.class);
                    }
                    //if not found, go to edit profile activity
//                    Intent editProfile = new Intent(GoogleLoginActivity.this, EditProfileActivity.class);
//                    GoogleLoginActivity.this.startActivity(editProfile);
//                    Toast.makeText(GoogleLoginActivity.this, "There is no user of this id", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            btn_signout.setVisibility(View.VISIBLE);

        } else {
            mStatusTextView.setText(new String("Login failed"));
            mDetailTextView.setText(new String ("Please try again"));
            btn_signout.setVisibility(View.INVISIBLE);
        }
    }

    public void signOutGoogle() {
        mAuth.signOut();

        // Google sign out
        mGoogleSignInClient.signOut().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        updateUI(null);
                    }
                });
        updateUI(null);
        Intent login = new Intent(GoogleLoginActivity.this, LoginActivity.class);
        GoogleLoginActivity.this.startActivity(login);
    }

}
