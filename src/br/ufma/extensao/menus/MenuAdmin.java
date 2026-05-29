package br.ufma.extensao.menus;

public class MenuAdmin extends MenuGeral {
    @Override
    public void imprimirEspecifico() {
        super.imprimirEspecifico();
        System.out.println("[1] Cadastrar novo coordenador");
        System.out.println("[2] Cadastrar novo docente");
        System.out.println("[3] Cadastrar novo grupo");
        System.out.println("[4] Cadastrar novo PPC");
        System.out.println("[5] Cadastrar nova UCE");
        System.out.println("[6] Desativar conta");
    }
}
