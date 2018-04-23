package com.programer.caio.pgp;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AddPedidoActivity extends Activity{
    private Button btnAddPedido;
    private EditText edtAddPedido;
    private  String nomeusuario;
    private String pedidouserId;
    private FirebaseAuth mAuth;


    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pedido);

        mFirebaseInstance = FirebaseDatabase.getInstance();

        mAuth = FirebaseAuth.getInstance();


        mFirebaseDatabase = mFirebaseInstance.getReference("pedidos");

        edtAddPedido = findViewById(R.id.edt_novopedido);
        btnAddPedido =  findViewById(R.id.btn_addPedido);


        btnAddPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String inputpedido = edtAddPedido.getText().toString().trim();

                if (TextUtils.isEmpty(inputpedido)) {
                    Toast.makeText(getApplicationContext(), "Coloque o seu pedido!", Toast.LENGTH_SHORT).show();

                }
                else{

                    String addpedido = inputpedido;

                        createPedido(addpedido);

                        Toast.makeText(AddPedidoActivity.this, "Pedido Adicionado" ,Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddPedidoActivity.this, MainActivity.class));
                    finish();

                }


    }
            });
    }

    private void createPedido(String addpedido) {

        pedidouserId = mAuth.getCurrentUser().getUid();
        nomeusuario = mAuth.getCurrentUser().getEmail();


        final Pedido pedidos = new Pedido(addpedido, nomeusuario, pedidouserId);
        mFirebaseDatabase.child(pedidouserId).setValue(pedidos);


    }

}

