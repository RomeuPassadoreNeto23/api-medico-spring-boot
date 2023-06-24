package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.medico.Especialidade;

public record DadosDetalhamentoConsulta(

		Long id,

		Long idMedico,

		Long idPaciente,

		LocalDateTime data,

		Especialidade especialidade

) {

}
