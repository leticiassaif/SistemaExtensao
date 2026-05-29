package br.ufma.extensao.menus;

public class MenuDD extends MenuDiscente {
    @Override
    public void imprimirEspecifico() {
        super.imprimirEspecifico();
        System.out.println("[] Criar oportunidade de extensão");
        System.out.println("[] Gerenciar inscrições");
        System.out.println("[] Substituir participante");

    }
}
