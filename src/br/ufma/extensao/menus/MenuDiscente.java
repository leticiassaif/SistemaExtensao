package br.ufma.extensao.menus;

public class MenuDiscente extends MenuGeral {

    @Override
    public void imprimirEspecifico() {
        super.imprimirEspecifico();
        System.out.println("[1] Autocadastro");
        System.out.println("[2] Criar liga");
        System.out.println("[3] Iniciar solicitação");
        System.out.println("[4] Reenviar solicitação");
        System.out.println("[5] Inscrever-se em oportunidade");
        System.out.println("[6] Cancelar inscrição");
        System.out.println("[7] Acompanhar status");
    }
}
