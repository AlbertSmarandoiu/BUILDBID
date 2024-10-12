package com.example.buildbid1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    EditText edUsername , edPassword;
    Button btnLogare , btnInregistrare;
    TextView tvLogo , tvLogin , tvDacaNuAiCont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

            edUsername = findViewById(R.id.editTextUsernameLogin);
            edPassword = findViewById(R.id.editTextLoginParola);
            btnLogare = findViewById(R.id.buttonLogin);
            btnInregistrare = findViewById(R.id.buttonregister);
            tvLogo = findViewById(R.id.textLogo);
            tvLogin = findViewById(R.id.textLoginTitle);
            tvDacaNuAiCont = findViewById(R.id.textDacaNuAiCont);
        Database db = new Database(getApplicationContext(),"healtcare" , null , 1);
            btnLogare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String username = edUsername.getText().toString();
                    String password = edPassword.getText().toString();
                    if(username.length() == 0 || password.length() == 0) {
                        Toast.makeText(getApplicationContext(), "Va rog sa introduceti in campul nume", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if (db.login(username, password) == 1) {
                            Toast.makeText(getApplicationContext(), "Logare cu Succes", Toast.LENGTH_SHORT).show();
                            SharedPreferences sharedpreferences = getSharedPreferences("shared_pref" , Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putString("username" , username);
                            editor.apply();
                            startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                        }else{
                            Toast.makeText(getApplicationContext(), "Logare nu a reusit cu succes ", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            });
            btnInregistrare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                }
            });
    }
}