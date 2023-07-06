package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    
	Boolean existsByPacienteIdAndDataBetween(Long idpaciente, LocalDateTime primeiroHorario , LocalDateTime utimoHorario);
	
	Boolean existsByMedicoIdAndData(Long idMedico, LocalDateTime data);
}
