package med.voll.api.domain.validacoes.consulta;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.infra.exception.ValidacaoException;
@Component("ValidadorHorarioAntecedencia")
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoDeConsulta {
	
	public void validar(DadosAgendamentoConsulta dados) {
		System.out.println("entrou aqui");
	 //pega a data  da consulta	
	LocalDateTime dataConsulta = dados.data();
	 //pega a data de agora
	 LocalDateTime agora = LocalDateTime.now();
	 //pega a diferenca
	  Long  diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();
	  System.out.println("minutos:  "+diferencaEmMinutos);
	 if(diferencaEmMinutos < 30) {
		 throw new ValidacaoException("Consulta deve ser agendada com antecedência mínima de 30 minutos");
		 
	 }
		
	}

}
