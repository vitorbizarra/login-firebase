package com.example.olavoproject;

import android.os.Bundle;
import android.os.Debug;
import android.util.DebugUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class RedefinirSenhaActivity extends AppCompatActivity {

    EditText txtEmail;

    Button btnSalvar;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redefinir_senha);

        txtEmail = (EditText) findViewById(R.id.editEmail);
        btnSalvar = (Button) findViewById(R.id.buttonSalvar);

        auth = FirebaseAuth.getInstance();

        btnSalvar.setOnClickListener(v -> {
            redefinirSenha();
        });
    }

    public void redefinirSenha()
    {
        String email = txtEmail.getText().toString();

        auth.sendPasswordResetEmail(email);

        Toast.makeText(RedefinirSenhaActivity.this, "Se existir uma conta com o email informado, um email será enviado para redefinição de senha.",
                Toast.LENGTH_LONG).show();
    }
}