package med.voll.api.domain.consulta.validacoes;

import org.springframework.beans.factory.annotation.Autowired;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.medico.MedicoRepository;

public class ValidadorMedicoAtivo {
	@Autowired
	private MedicoRepository repository;
	public void validar(DadosAgendamentoConsulta dados) {
		if(dados.idMedico() == null) {
			return ;
		}
	      Boolean medicoEstadoAtivo = repository.findByAtivoById(dados.idMedico());
	      if(!medicoEstadoAtivo) {
	    	  throw new ValidacaoException("Consulta não pode ser agendada com médico que não estiver ativo");
	    	  
	      }
	}

}
