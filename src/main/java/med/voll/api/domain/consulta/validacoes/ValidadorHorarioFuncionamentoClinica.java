package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class ValidadorHorarioFuncionamentoClinica {

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
