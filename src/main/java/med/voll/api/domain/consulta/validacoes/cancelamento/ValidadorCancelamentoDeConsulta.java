package med.voll.api.domain.consulta.validacoes.cancelamento;

import org.springframework.stereotype.Component;

import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
@Component("ValidadorHorarioAntecedenciaCancelamento")
public interface ValidadorCancelamentoDeConsulta {
           void validar(DadosCancelamentoConsulta dados);
}
