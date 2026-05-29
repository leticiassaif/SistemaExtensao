package br.ufma.extensao.menus;

public class MenuDiscente extends MenuGeral {

    @Override
    public void imprimirEspecifico() {
        super.imprimirEspecifico();
        System.out.println("[] Autocadastro");
        System.out.println("[] Criar liga");
        System.out.println("[] Inscrever-se em oportunidade");
        System.out.println("[] Cancelar inscrição");
        System.out.println("[] Iniciar solicitação");
        System.out.println("[] Reenviar solicitação");
        System.out.println("[] Acompanhar status");
    }
}
