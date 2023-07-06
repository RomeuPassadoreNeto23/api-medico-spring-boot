package med.voll.api.domain.consulta.validacoes;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;

public class ValidadorPacienteSemOutraConsultaNoDia {
	@Autowired
	private ConsultaRepository repository;
	private void Validador(DadosAgendamentoConsulta dados) {
		LocalDateTime primeiroHorario = dados.data().withHour(7);
		LocalDateTime utimoHorario = dados.data().withHour(18);
		Boolean pacientePossuiOutraConsultaNoDia = repository.existsByPacienteIdAndDataBetween(dados.idPaciente(), primeiroHorario, utimoHorario);
		if(pacientePossuiOutraConsultaNoDia) {
			throw new ValidacaoException("Paciente já possui uma consulta nesse dia");
		}
	}

}
