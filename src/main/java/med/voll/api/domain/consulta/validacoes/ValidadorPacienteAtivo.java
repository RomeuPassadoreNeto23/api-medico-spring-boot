package med.voll.api.domain.consulta.validacoes;

import org.springframework.beans.factory.annotation.Autowired;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.paciente.PacienteRepository;

public class ValidadorPacienteAtivo {
	@Autowired
	private PacienteRepository repository;
	private void validador(DadosAgendamentoConsulta dados) {
		Boolean pacienteEstaAtivo = repository.findAtivoById(dados.idPaciente());
		if(!pacienteEstaAtivo) {
		throw new ValidacaoException("Consulta não pode ser agendado com paciente excluido ");
		}
	}

}
