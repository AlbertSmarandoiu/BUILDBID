package com.example.buildbid1;

import android.content.Intent;
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

public class RegisterActivity extends AppCompatActivity {

    EditText edUsername , edEmail , edPassword , edConfirmPassword;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edUsername = findViewById(R.id.editTextUsernameInregistrare);
        edEmail = findViewById(R.id.editTextInregistrareEmail);
        edPassword = findViewById(R.id.editTextInrestrareParola);
        edConfirmPassword = findViewById(R.id.editTextInrestrareParola2);
        btn = findViewById(R.id.buttonInregistrare);
        tv = findViewById(R.id.textInregistrareTitlu);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edUsername.getText().toString();
                String password = edPassword.getText().toString();
                String confirmPassword = edConfirmPassword.getText().toString();
                String email = edEmail.getText().toString();
                Database db = new Database(getApplicationContext(),"healtcare" , null , 1);
                if(username.length() == 0 || password.length() == 0 || email.length() == 0 ) {
                    Toast.makeText(getApplicationContext(), "Va rog sa introduceti in campul nume", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(password.compareTo(confirmPassword) == 0 ){
                        if(isValid(password)){
                            db.register(username,email,password);
                            Toast.makeText(getApplicationContext() , "Contul a fost creat" , Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                        }else{
                            Toast.makeText(getApplicationContext() , "Parola nu are litere mari sau numere in ea" , Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext() , "Parolele nu concid" , Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });


    }
    public static boolean isValid(String password){
        int try1=0 , try2=0 , try3=0;
        if(password.length() < 8){
            return false;
        }
        else{
            for(int p = 0 ; p < password.length() ; p++){
                if(Character.isLetter(password.charAt(p))){
                    try1 =1;
                }
            }
            for(int r = 0 ; r < password.length() ; r++){
                if(Character.isDigit(password.charAt(r))){
                    try2 = 1;
                }
            }
            for(int s = 0 ; s<password.length() ; s++){
                char c = password.charAt(s);
                if(c>=33 && c<=46 || c==64){
                    try3 = 1;
                }
            }
        }
        if(try1==1 && try2 == 1 && try3 == 1){
            return true;
        }
        return false;
    }
}