package com.example.arborshoes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	//Declaring Objects
	EditText textUserName;
	EditText textPassword;
	Button buttonLogin;
	Button buttonRegister;

	private FirebaseAuth mAuth;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Initialize Firebase Auth
		mAuth = FirebaseAuth.getInstance();

		//Linking objects with UI objects
		textUserName = findViewById(R.id.textUserName);
		textPassword = findViewById(R.id.textPassword);
		buttonLogin = findViewById(R.id.buttonLogin);
		buttonRegister = findViewById(R.id.buttonRegister);

		//Adding click listeners to buttons
		buttonLogin.setOnClickListener(this);
		buttonRegister.setOnClickListener(this);
	}

	@Override
	public void onStart() {
		super.onStart();
		// Check if user is signed in already signed in and take him to Portal Activity directly.
		FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
		if(currentUser != null) {
            Intent intent = new Intent(MainActivity.this, PortalActivity.class);
            startActivity(intent);
        }
	}


	@Override
	public void onClick(View v) {
		String email = textUserName.getText().toString();
		String password = textPassword.getText().toString();

		if(v == buttonRegister){
			mAuth.createUserWithEmailAndPassword(email, password)
					.addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
						@Override
						public void onComplete(@NonNull Task<AuthResult> task) {
							if (task.isSuccessful()) {
								// Display success message when registration is successful.
								Toast.makeText(MainActivity.this, "Registration Successful. Please Login!", Toast.LENGTH_SHORT).show();
							} else {
								// Display error when registration fails.
								Toast.makeText(MainActivity.this, "Registration Failed. Try again!", Toast.LENGTH_SHORT).show();
							}
						}
					});

		} else if(v == buttonLogin){
			mAuth.signInWithEmailAndPassword(email, password)
					.addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
						@Override
						public void onComplete(@NonNull Task<AuthResult> task) {
							if (task.isSuccessful()) {
								// Take user to Portal Activity when login is successful
								Intent intent = new Intent(MainActivity.this, PortalActivity.class);
								startActivity(intent);
							} else {
								// If sign in fails, display a message to the user.
								Toast.makeText(MainActivity.this, "Login Failed. Try again!", Toast.LENGTH_SHORT).show();
							}
						}
					});
		}

	}
}
