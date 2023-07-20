package med.voll.api.domain.validacoes.consulta;

import org.springframework.stereotype.Component;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;


public interface ValidadorAgendamentoDeConsulta {
  void validar(DadosAgendamentoConsulta dados);
}
