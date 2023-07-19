package med.voll.api.domain.consulta.validacoes;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.infra.exception.ValidacaoException;
@Component
public class ValidadorPacienteSemOutraConsultaNoDia  implements ValidadorAgendamentoDeConsulta{
	@Autowired
	private ConsultaRepository repository;
	public void validar (DadosAgendamentoConsulta dados) {
		LocalDateTime primeiroHorario = dados.data().withHour(7);
		LocalDateTime utimoHorario = dados.data().withHour(18);
		Boolean pacientePossuiOutraConsultaNoDia = repository.existsByPacienteIdAndDataBetween(dados.idPaciente(), primeiroHorario, utimoHorario);
		if(pacientePossuiOutraConsultaNoDia) {
			throw new ValidacaoException("Paciente já possui uma consulta nesse dia");
		}
	}

}
