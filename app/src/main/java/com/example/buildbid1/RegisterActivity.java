package com.example.buildbid1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    EditText edUsername, edEmail, edPassword, edConfirmPassword;
    Button btnRegister;
    TextView tvTitle;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        edUsername = findViewById(R.id.editTextUsernameInregistrare);  // You can use this if you plan to use the username elsewhere
        edEmail = findViewById(R.id.editTextInregistrareEmail);
        edPassword = findViewById(R.id.editTextInrestrareParola);
        edConfirmPassword = findViewById(R.id.editTextInrestrareParola2);
        btnRegister = findViewById(R.id.buttonInregistrare);
        tvTitle = findViewById(R.id.textInregistrareTitlu);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edEmail.getText().toString();
                String password = edPassword.getText().toString();
                String confirmPassword = edConfirmPassword.getText().toString();
                String username = edUsername.getText().toString();  // Optional if you're not using it yet

                if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Completeaza toate campurile", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(confirmPassword)) {
                    Toast.makeText(RegisterActivity.this, "Parolele nu sunt identice", Toast.LENGTH_SHORT).show();
                } else if (!isValid(password)) {
                    Toast.makeText(RegisterActivity.this, "Parola trebuie sa contina minim 8 caractere, o litera, un numar si un caracter special", Toast.LENGTH_SHORT).show();
                } else {
                    registerUser(email, password);
                }
            }
        });
    }

    // Register user with Firebase Authentication
    private void registerUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "Te-ai inregistrat cu succes!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        finish();
                    } else {
                        // If registration fails, display an error message
                        Toast.makeText(RegisterActivity.this, "Inregistrare esuata: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // Password validation method (same as your original one)
    public static boolean isValid(String password) {
        int try1 = 0, try2 = 0, try3 = 0;
        if (password.length() < 8) {
            return false;
        } else {
            for (int p = 0; p < password.length(); p++) {
                if (Character.isLetter(password.charAt(p))) {
                    try1 = 1;
                }
            }
            for (int r = 0; r < password.length(); r++) {
                if (Character.isDigit(password.charAt(r))) {
                    try2 = 1;
                }
            }
            for (int s = 0; s < password.length(); s++) {
                char c = password.charAt(s);
                if (c >= 33 && c <= 46 || c == 64) {
                    try3 = 1;
                }
            }
        }
        return try1 == 1 && try2 == 1 && try3 == 1;
    }
}
