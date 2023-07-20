package med.voll.api.domain.consulta.validacoes.cancelamento;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import med.voll.api.infra.exception.ValidacaoException;
@Component("ValidorCancelamentoHoraioAntecedencia")
public class ValidorCancelamentoHoraioAntecedencia implements ValidadorCancelamentoDeConsulta {
	@Autowired
	private ConsultaRepository repository;
	
	public void validar(DadosCancelamentoConsulta dados) {
		
		// pega a data da consulta com Id da consulta
		Consulta consulta = repository.getReferenceById(dados.idConsulta());
		
		// pega a data de Agora
		LocalDateTime dataDeAgora = LocalDateTime.now();
		
		// pega a diferamça em Horas
		Long diferencaEmHoras = Duration.between(dataDeAgora, consulta.getData()).toHours();
		
		if(diferencaEmHoras < 24) {

			throw new ValidacaoException(" consulta somente poderá ser cancelada com antecedência mínima de 24 horas");
			
		}
		
		
		
	}

}
