package br.ufma.extensao.servicos;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SenhaHash {

    private SenhaHash() {}; // impede instanciação — só métodos estáticos

    public static String hash(String senha) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = digest.digest(senha.getBytes());
            StringBuilder sb = new StringBuilder();

            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
