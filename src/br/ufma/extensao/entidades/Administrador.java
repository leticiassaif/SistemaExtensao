package br.ufma.extensao.entidades;

import br.ufma.extensao.enums.Papel;

import java.util.UUID;

public class Administrador extends Usuario {
    public Administrador(String nome, String email, String senha) {
        super("ADM-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase(), nome, email, senha, Papel.ADMIN);
    }
}
