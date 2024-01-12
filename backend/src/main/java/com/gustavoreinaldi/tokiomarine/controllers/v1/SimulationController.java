package com.gustavoreinaldi.tokiomarine.controllers.v1;

import com.gustavoreinaldi.tokiomarine.dto.TransferSimulationDTO;
import com.gustavoreinaldi.tokiomarine.services.TransferSimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("v1/simulate-taxes")
public class SimulationController {
    private TransferSimulationService transferSimulationService;

    @Autowired
    public SimulationController(TransferSimulationService transferSimulationService) {
        this.transferSimulationService = transferSimulationService;
    }

    @GetMapping
    public ResponseEntity<TransferSimulationDTO> simulateTaxes (@RequestParam("schedule-date") LocalDate date,
                                                                @RequestParam("transfer-amount") Double amount) {
        return ResponseEntity.ok(transferSimulationService.simulate(date, amount));
    }
}
