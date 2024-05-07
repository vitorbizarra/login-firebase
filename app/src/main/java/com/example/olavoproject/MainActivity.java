package com.example.olavoproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.example.olavoproject.models.Usuario;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton buttonAdd;
    FirebaseFirestore db;
    EditText txtNome,txtSobrenome,txtAno,txtFoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = FirebaseFirestore.getInstance();
        txtAno = (EditText)findViewById(R.id.editTextAno);
        txtFoto = (EditText)findViewById(R.id.editTextFoto);
        txtNome = (EditText)findViewById(R.id.editTextNome);
        txtSobrenome =(EditText)findViewById(R.id.editTextSobrenome);

        if(getIntent().getExtras() != null){
            txtAno.setText(""+ getIntent().getIntExtra("anoNascimento",0));
            txtNome.setText(getIntent().getStringExtra("nome"));
            txtFoto.setText(getIntent().getStringExtra("foto"));
            txtSobrenome.setText(getIntent().getStringExtra("sobrenome"));
        }

        buttonAdd = (FloatingActionButton) findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(v -> {
            Usuario usu = new Usuario();
            usu.setFoto(txtFoto.getText().toString());
            usu.setId(getIntent().getStringExtra("id"));
            usu.setNome(txtNome.getText().toString());
            usu.setSobrenome(txtSobrenome.getText().toString());
            usu.setAnoNascimento(
                    Integer.parseInt(txtAno.getText().toString()));

            if(getIntent().getExtras()!= null){
                //edição
                db.collection("usuarios").document(usu.getId()).set(usu);
            }else{
                //inserção
                db.collection("usuarios").add(usu);
            }
        });
    }
}