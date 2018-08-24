package edu.bluejack17_2.water18.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.facebook.CallbackManager
import edu.bluejack17_2.water18.R
import kotlinx.android.synthetic.main.activity_login.*
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.FacebookCallback
import com.facebook.AccessToken
import com.facebook.login.LoginManager
import java.util.*

import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import android.R.attr.data
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.ConnectionResult
import android.support.annotation.NonNull
import android.util.Log
import com.google.android.gms.common.api.ApiException
import android.R.attr.data
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.*


class LoginActivity : AppCompatActivity(), View.OnClickListener, GoogleApiClient.OnConnectionFailedListener
{
    private val RC_SIGN_IN = 9001
    private val callbackManager = CallbackManager.Factory.create()
    private var mGoogleApiClient: GoogleApiClient? = null
    private var mAuth = FirebaseAuth.getInstance()

    override fun onConnectionFailed(connectionResult: ConnectionResult) {
        Log.d("Umm", "onConnectionFailed: " + connectionResult)
    }

    private fun addListener()
    {
        val buttons=arrayOf(btn_login ,btn_login_facebook ,btn_login_google ,btn_sign_up)
        buttons.forEach { it.setOnClickListener(this) }
    }

    override fun onStart() {
        super.onStart()
        //LoginManager.getInstance().logOut()
        //check if there is a Facebook Acount signed in
        val accessToken = AccessToken.getCurrentAccessToken()
        val isLoggedIn = accessToken != null && !accessToken.isExpired
        if(isLoggedIn){
            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"))
        }

        //check if there is a Google Acount signed in
        val account = GoogleSignIn.getLastSignedInAccount(this)
        //if account is not null means a user has signed in before
        if (account != null) {
            //go to main activity

        }
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        addListener()

// ...

        //google
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        mGoogleApiClient = GoogleApiClient.Builder(this).enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()

        //facebook
        btn_login_facebook.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                // App code
                val intent=Intent(applicationContext,CustomerMainActivity::class.java)
                startActivity(intent)
            }

            override fun onCancel() {
                // App code
            }

            override fun onError(exception: FacebookException) {
                // App code
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //facebook
        callbackManager.onActivityResult(requestCode, resultCode, data)

        //google
        if (requestCode == RC_SIGN_IN) {

            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                //Log.w(FragmentActivity.TAG, "Google sign in failed", e)
                // ...
            }

//
        }
    }

    override fun onClick(src: View?)
    {
        when(src){
            btn_sign_up->signUp()
            btn_login->login()
            btn_login_facebook->loginWithFacebook()
            btn_login_google ->loginWithGoogle()
            else -> return
        }
    }

    fun signUp()
    {
        //val intent=Intent(applicationContext,SignUpWithPhoneNumberActivity::class.java)
        val intent=Intent(applicationContext,CustomerMainActivity::class.java)
        startActivity(intent)
    }

    fun login()
    {
        val phone=tf_phone_login.text
        val password=pf_password.text
        //val intent=Intent(applicationContext,CustomerMainActivity::class.java)
        val intent=Intent(applicationContext,AdminMainActivity::class.java)
        startActivity(intent)
    }

    fun loginWithFacebook()
    {

    }

    fun loginWithGoogle()
    {
        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        //Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        var credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null)
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, OnCompleteListener <AuthResult> {

            fun onComplete(@NonNull task: Task<AuthResult> ) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    //Log.d(TAG, "signInWithCredential:success");
                    var user = mAuth.currentUser;
                    Toast.makeText(this, "Loggin success.",
                            Toast.LENGTH_LONG).show();
                    val intent=Intent(applicationContext,CustomerMainActivity::class.java)
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    //Log.w(TAG, "signInWithCredential:failure", task.getException());
                    Toast.makeText(this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();

                }

                // ...
            }
        })
    }


}
