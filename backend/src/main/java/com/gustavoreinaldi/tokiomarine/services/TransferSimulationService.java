package com.gustavoreinaldi.tokiomarine.services;

import com.gustavoreinaldi.tokiomarine.dto.TransferSimulationDTO;
import com.gustavoreinaldi.tokiomarine.useCases.TransferCalculationUseCase;
import com.gustavoreinaldi.tokiomarine.utils.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service("TransferSimulationService")
public class TransferSimulationService {
    private TransferCalculationUseCase transferCalculationUseCase;

    @Autowired
    public TransferSimulationService(TransferCalculationUseCase transferCalculationUseCase) {
        this.transferCalculationUseCase = transferCalculationUseCase;
    }
    public TransferSimulationDTO simulate (LocalDate date, double amount) {
        Double calculatedValue = transferCalculationUseCase.calculateTransferValueWithTaxAdded(date, amount);
        Double tax = NumberUtils.twoDecimalsOnly(calculatedValue - amount);
        return TransferSimulationDTO.builder()
                .valueToTransfer(amount)
                .taxesTransfer(tax)
                .subTotal(amount + tax)
                .build();
    }
}
