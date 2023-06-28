package med.voll.api.controller;

import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.AgendaDeConsultas;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.DadosDetalhamentoConsulta;

@RestController
@RequestMapping("consulta")
public class ConsultaController {
	@Autowired
	private AgendaDeConsultas agenda;
	
	@PostMapping
	@Transactional
	public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
		
		agenda.agendar(dados);
		return ResponseEntity.ok(new DadosDetalhamentoConsulta(null, null, null, null, null));
		
		
		
	}
	@DeleteMapping
	@Transactional
	public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados){
		return  ResponseEntity.noContent().build();

	}

}
