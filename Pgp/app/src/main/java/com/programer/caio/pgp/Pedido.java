package com.programer.caio.pgp;


import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Pedido {


    public String pedido;
    public String nomeusuario;
    public String pedidoUserId;



    public Pedido() {
    }

    public Pedido(String pedido, String nomeusuario, String pedidoUserId) {
        this.pedido = pedido;
        this.nomeusuario = nomeusuario;
        this.pedidoUserId = pedidoUserId;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("pedido", pedido);
        result.put("nomeusuario", nomeusuario);
        result.put("pedidoUserId", pedidoUserId);


        return result;
    }

}
