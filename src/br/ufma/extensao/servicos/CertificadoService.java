package br.ufma.extensao.servicos;

import br.ufma.extensao.entidades.Certificado;
import br.ufma.extensao.entidades.Discente;
import br.ufma.extensao.entidades.Oportunidade;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CertificadoService {
    private List<Certificado> certificados = new ArrayList<>();
    private long proximoId = 1;

    public Certificado gerar(Discente discente, Oportunidade oportunidade, double cargaHoraria, LocalDate dataEmissao){

        if (discente == null || oportunidade == null)
            throw new IllegalArgumentException("Discente e oportunidade são obrigatórios.");
        if (cargaHoraria <= 0)
            throw new IllegalArgumentException("Carga horária deve ser positiva.");
        if (dataEmissao == null || dataEmissao.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("Data de emissão inválida.");

        Long id = proximoId++;
        Certificado certificado = new Certificado(id, discente, oportunidade, cargaHoraria, dataEmissao);
        certificados.add(certificado);
        return certificado;
    }

    public Certificado buscar(String codigo){
        for (Certificado c : certificados){
            if(c.getCodigoAutenticidade().equals(codigo))
                return c;
        }
        return null;
    }

    public List <Certificado> listarPorDiscente(Discente discente){
        List<Certificado> resultado = new ArrayList<>();
        for (Certificado c : certificados){
            if(c.getDiscente().equals(discente)){
                resultado.add(c);
            }
        }
        return resultado;
    }
}
