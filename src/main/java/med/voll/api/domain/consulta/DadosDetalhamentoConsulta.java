package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import med.voll.api.domain.medico.Especialidade;

public record DadosDetalhamentoConsulta(

		Long id,

		Long idMedico,

		Long idPaciente,

		LocalDateTime data,
        
		 @JsonIgnore
		Especialidade especialidade

) {

	public DadosDetalhamentoConsulta(Consulta consulta) {
		this(consulta.getId(), consulta.getMedico().getId(),consulta.getPaciente().getId(),consulta.getData(), null);
	}

	

}
