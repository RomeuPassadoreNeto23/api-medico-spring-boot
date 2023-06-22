package med.voll.api.domain.consulta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteRepository;

@Service
public class AgendaDeConsultas {
	
	@Autowired
	private ConsultaRepository consultaRepository;
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private MedicoRepository medicoRepository;
	
	public void agendar(DadosAgendamentoConsulta dados) {
	Paciente paciente =  pacienteRepository.findById(dados.idPaciente()).get();
	Medico medico = medicoRepository.findById(dados.idMedico()).get();
	Consulta consulta = new Consulta(null,medico,paciente,dados.data());
		consultaRepository.save(consulta);
	}

}
