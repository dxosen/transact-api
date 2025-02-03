package com.dxosen.transact_api.business.services;


import com.dxosen.transact_api.controller.dtos.EstatisticasResponseDto;
import com.dxosen.transact_api.controller.dtos.TransacaoRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EstatiticasService {

    public final TransacaoService transacaoService;

    public EstatisticasResponseDto calcularEstatisticasTransacoes(Integer intervaloBusca) {

        log.info("Iniciada Busca de Estatisticas pelo periodo {}", intervaloBusca);
        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoes(intervaloBusca);


        if(transacoes.isEmpty()) {
            return new EstatisticasResponseDto(0L, 0.0, 0.0, 0.0, 0.0);
        }


        DoubleSummaryStatistics estatisticasTransacoes = transacoes.stream()
                .mapToDouble(TransacaoRequestDTO::valor).summaryStatistics();

        log.info("Sucesso! Finalizada Busca de Estatisticas");
        return new EstatisticasResponseDto(estatisticasTransacoes.getCount(),
                estatisticasTransacoes.getSum(),
                estatisticasTransacoes.getAverage(),
                estatisticasTransacoes.getMin(),
                estatisticasTransacoes.getMax()
        );
    }

}
