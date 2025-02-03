package com.dxosen.transact_api.controller;

import com.dxosen.transact_api.business.services.EstatiticasService;
import com.dxosen.transact_api.controller.dtos.EstatisticasResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estatistica")
@RequiredArgsConstructor
public class EstatisticasController {

    private final EstatiticasService estatiticasService;


    @GetMapping
    @Operation(description = "Endpoint resposavel por buscar estatisticas de transacao")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "busca efetuada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na busca de estatisticas de transacoes"),
            @ApiResponse(responseCode = "500", description = "Erro interno")
    })
    public ResponseEntity<EstatisticasResponseDto> buscarEstatisticas(
            @RequestParam(value = "intervaloBusca", required = false, defaultValue = "60") Integer intervaloBusca) {
        return ResponseEntity.ok(estatiticasService.calcularEstatisticasTransacoes(intervaloBusca));
    }

}
