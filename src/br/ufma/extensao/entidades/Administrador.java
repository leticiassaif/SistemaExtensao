package br.ufma.extensao.entidades;

import br.ufma.extensao.enums.Papel;

public class Administrador extends Usuario {
    public Administrador(String nome, String email, String senha) {
        super("ADM-", nome, email, senha, Papel.ADMIN);
    }
}
