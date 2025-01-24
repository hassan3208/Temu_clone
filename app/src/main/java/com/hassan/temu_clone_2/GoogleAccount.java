package com.hassan.temu_clone_2;


import android.app.Activity;
import android.content.Intent;
import android.util.Log;

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

import java.util.List;
import java.util.concurrent.Executor;

public class GoogleAccount {

    private final GoogleSignInClient googleSignInClient;
    private final FirebaseAuth firebaseAuth;

    public GoogleAccount(Activity activity) {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(activity.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(activity, gso);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    // Method for Sign-Up with Google
    public void signUpWithGoogle(Activity activity, int requestCode) {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        activity.startActivityForResult(signInIntent, requestCode);
    }

    // Method for Logging in with Google
    public void loginWithGoogle(Intent data, OnCompleteListener<AuthResult> onCompleteListener) {
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            if (account != null) {
                AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
                firebaseAuth.signInWithCredential(credential)
                        .addOnCompleteListener(onCompleteListener);
            }
        } catch (ApiException e) {
            Log.e("GoogleAccount", "Google Sign-In failed", e);
        }
    }


    // Create GoogleUser object
    public GoogleUser createGoogleUser(FirebaseUser firebaseUser, List<String> cartItems) {
        return new GoogleUser(firebaseUser.getDisplayName(), firebaseUser.getEmail(), firebaseUser.getPhotoUrl().toString(), cartItems);
    }



    public void signOut(settings_next settingsNext) {
        // Clear Google sign-in state
        googleSignInClient.signOut().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // Clear Firebase authentication state if needed
                FirebaseAuth.getInstance().signOut();
                Log.d("GoogleAccount", "Sign-out completed successfully");
                Intent intent = new Intent(settingsNext, login_page.class);
                settingsNext.startActivity(intent);
                settingsNext.finish();
            } else {
                Log.e("GoogleAccount", "Sign-out failed", task.getException());
            }
        });
    }





    // check is the user signed in or not
    public boolean isUserSignedIn() {
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        return currentUser != null;
    }
}
