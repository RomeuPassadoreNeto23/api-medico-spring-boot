package med.voll.api.domain.validacoes.consulta;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.infra.exception.ValidacaoException;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;
@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoDeConsulta {

    public void  validar(DadosAgendamentoConsulta dados){
        //pega a data da consulta
        LocalDateTime  dataConsulta = dados.data();
        //verifica se o dia da semana e domingo
        Boolean  domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        //verifica  se data e antes da 7 Horas
        Boolean antesDaAberturaDaClinica = dataConsulta.getHour() < 7;
        //verifica se a data e depois da 18 horas
        Boolean  depoisDoEncerramentoDaClinica = dataConsulta.getHour() > 18;
        if(domingo || antesDaAberturaDaClinica || depoisDoEncerramentoDaClinica ) {
        	throw new  ValidacaoException("Consulta fora do Horário de funcionamento da clinica");
        }


    }
}
