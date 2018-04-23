package com.programer.caio.pgp;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Usuario {



    public String name;
    public String email;
    public String senha;
    public String sobrenome;


    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public Usuario() {
    }

    public Usuario(String name, String email, String senha, String sobrenome) {
        this.name = name;
        this.email = email;
        this.senha = senha;
        this.sobrenome= sobrenome;
    }



        public String getNome(String s) {
            return name;
        }

        public void setNome(String nome) {
            this.name = nome;
        }


        public void setSobrenome(String sobrenome) {
            this.sobrenome = sobrenome;
        }

        public String getSobrenome(String s) {
            return sobrenome;
        }

        public String getEmail(String s) {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getSenha(String s) {
            return senha;
        }

        public void setSenha(String senha) {
            this.senha = senha;
        }
}
