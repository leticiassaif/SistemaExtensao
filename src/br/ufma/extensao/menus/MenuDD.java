package br.ufma.extensao.menus;

public class MenuDD extends MenuDiscente {
    @Override
    public void imprimirEspecifico() {
        super.imprimirEspecifico();
        System.out.println("[8] Criar oportunidade de extensão");
        System.out.println("[9] Gerenciar inscrições");
        System.out.println("[10] Substituir participante");

    }
}
