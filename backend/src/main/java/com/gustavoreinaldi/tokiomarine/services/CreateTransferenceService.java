package com.gustavoreinaldi.tokiomarine.services;

import com.gustavoreinaldi.tokiomarine.dto.input.TransferInputDTO;
import com.gustavoreinaldi.tokiomarine.entities.TransferEntity;
import com.gustavoreinaldi.tokiomarine.repositories.TransferRepository;
import com.gustavoreinaldi.tokiomarine.useCases.TransferCalculationUseCase;
import com.gustavoreinaldi.tokiomarine.utils.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Date;

@Service
public class CreateTransferenceService {
    private TransferCalculationUseCase transferCalculationUseCase;
    private TransferRepository transferRepository;

    @Autowired
    public CreateTransferenceService(TransferCalculationUseCase transferCalculationUseCase, TransferRepository transferRepository) {
        this.transferCalculationUseCase = transferCalculationUseCase;
        this.transferRepository = transferRepository;
    }

    @Transactional
    public void doTransfer(TransferInputDTO transferData) {
        Double valueWithTax = transferCalculationUseCase
                .calculateTransferValueWithTaxAdded(transferData.getScheduledDate(),
                        transferData.getTransferAmount());
        var newTransfer = TransferEntity.builder()
                .transferDate(LocalDate.now())
                .transferTax(NumberUtils.twoDecimalsOnly(valueWithTax - transferData.getTransferAmount()))
                .transferAmount(transferData.getTransferAmount())
                .destinationAccount(transferData.getDestinationAccount())
                .originAccount(transferData.getOriginAccount())
                .scheduledDate(transferData.getScheduledDate())
                .build();
        transferRepository.save(newTransfer);
    }
}
