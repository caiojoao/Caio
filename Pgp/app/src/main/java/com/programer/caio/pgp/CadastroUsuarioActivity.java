package com.programer.caio.pgp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CadastroUsuarioActivity extends AppCompatActivity {

    private EditText nome;
    private EditText sobrenome;
    private EditText email;
    private EditText senha;
    private Button btnCadastrar;
    private String userId;

    private FirebaseAuth mAuth;

    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        nome = findViewById(R.id.edt_Nome);
        sobrenome = findViewById(R.id.edt_addSobrenome);
        email =findViewById(R.id.edt_addEmail);
        senha = findViewById(R.id.edt_Senha);
        btnCadastrar =findViewById(R.id.btn_salvarUsuario);

        mFirebaseInstance = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();

        mFirebaseDatabase = mFirebaseInstance.getReference("users");

        mFirebaseInstance.getReference("app_title").setValue("Realtime Database");



        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String inputemail = email.getText().toString().trim();
                String password = senha.getText().toString().trim();
                if (TextUtils.isEmpty(inputemail)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }


                //create user
                mAuth.createUserWithEmailAndPassword(inputemail, password)
                        .addOnCompleteListener(CadastroUsuarioActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(CadastroUsuarioActivity.this, "Usuário criado com sucesso:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();

                                if (!task.isSuccessful()) {
                                    Toast.makeText(CadastroUsuarioActivity.this, "Não foi possivél realizar o cadastro" + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {


                                    // Save / update the user

                                            String name2 = nome.getText().toString();
                                            String inputemail2 = email.getText().toString();
                                            String inputsenha = senha.getText().toString();
                                            String inputsobrenome = sobrenome.getText().toString();

                                            // Check for already existed userId
                                            if (TextUtils.isEmpty(userId)) {
                                                createUser(name2, inputemail2,inputsenha, inputsobrenome);
                                            } else {
                                                Toast.makeText(CadastroUsuarioActivity.this, "Este usuário já existe" + task.getException(),
                                                        Toast.LENGTH_SHORT).show();
                                            }


                                    startActivity(new Intent(CadastroUsuarioActivity.this, MainActivity.class));
                                    finish();
                                }
                            }
                        });

            }
        });
    }

    private void createUser(String name, String email, String senha, String sobrenome) {
        // TODO
        // In real apps this userId should be fetched
        // by implementing firebase auth
        if (TextUtils.isEmpty(userId)) {
            userId = mAuth.getCurrentUser().getUid();
        }

        Usuario user = new Usuario(name, email, senha, sobrenome);

        mFirebaseDatabase.child(userId).setValue(user);

        addUserChangeListener();
    }

    private void addUserChangeListener() {
        // User data change listener
        mFirebaseDatabase.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Usuario user = dataSnapshot.getValue(Usuario.class);

                // Check for null
                if (user == null) {
                    Log.e(TAG, "User data is null!");
                    return;
                }

                Log.e(TAG, "User data is changed!" + user.name + ", " + user.email);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read user", error.toException());
            }
        });
    }

    private void updateUser(String name, String email, String senha, String sobrenome) {
        // updating the user via child nodes
        if (!TextUtils.isEmpty(name))
            mFirebaseDatabase.child(userId).child("name").setValue(name);

        if (!TextUtils.isEmpty(email))
            mFirebaseDatabase.child(userId).child("email").setValue(email);
        if (!TextUtils.isEmpty(senha))
            mFirebaseDatabase.child(userId).child("senha").setValue(senha);
        if (!TextUtils.isEmpty(sobrenome))
            mFirebaseDatabase.child(userId).child("sobrenome").setValue(sobrenome);
    }



        public void abrirLogin(View view){
            Intent intent = new Intent(CadastroUsuarioActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
    }


}
