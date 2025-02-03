package com.dxosen.transact_api.business.services;

import com.dxosen.transact_api.controller.dtos.TransacaoRequestDTO;
import com.dxosen.transact_api.infrastructure.exceptions.UnprocessableEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransacaoService {

private final List<TransacaoRequestDTO> listaTransacoes = new ArrayList<>();

public void adicionarTransacao(final TransacaoRequestDTO dto) {

    log.info("iniciado processamento de gravar transacoes {}", dto);


    if(dto.dataHora().isAfter(OffsetDateTime.now())){
        log.info("data e hora maiores que a data e hora atual");
        throw new UnprocessableEntity("data e hora maiores que a data e hora atuais");
    }
    if(dto.valor()<0){
        log.info("valor negativo");
        throw new UnprocessableEntity("valor negativo");
    }
    listaTransacoes.add(dto);
    log.info("transacoes adicionada com sucesso");
}

public void limparTransacoes() {
    log.info("iniciado processamento de limpar transacoes");
    listaTransacoes.clear();
    log.info("transacoes limpada com sucesso");
}


public  List<TransacaoRequestDTO> buscarTransacoes(Integer intervaloBusca) {
    log.info("iniciado processamento de buscar transacoes por intervalo {}", intervaloBusca);
    OffsetDateTime dataHoraIntervalo = OffsetDateTime.now().minusSeconds(intervaloBusca);

    log.info("Busca de transacoes feita com sucesso");
    return listaTransacoes.stream()
        .filter(transacao -> transacao.dataHora()
                .isAfter(dataHoraIntervalo)).toList();
}
}
