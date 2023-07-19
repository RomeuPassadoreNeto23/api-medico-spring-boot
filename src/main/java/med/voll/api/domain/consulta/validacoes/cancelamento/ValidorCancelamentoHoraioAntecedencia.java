package med.voll.api.domain.consulta.validacoes.cancelamento;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import med.voll.api.infra.exception.ValidacaoException;

public class ValidorCancelamentoHoraioAntecedencia implements ValidadorCancelamentoDeConsulta {
	@Autowired
	private ConsultaRepository repository;
	
	public void validar(DadosCancelamentoConsulta dados) {
		
		// pega a data da consulta com Id da consulta
		LocalDateTime dataDaConsulta = repository.findByDataById(dados.idConsulta());
		
		// pega a data de Agora
		LocalDateTime dataDeAgora = LocalDateTime.now();
		
		// pega a diferamcia em Horas
		Long diferencaEmHoras = Duration.between(dataDeAgora, dataDaConsulta).toHours();
		
		if(diferencaEmHoras < 24) {

			throw new ValidacaoException(" consulta somente poderá ser cancelada com antecedência mínima de 24 horas");
			
		}
		
		
		
	}

}
