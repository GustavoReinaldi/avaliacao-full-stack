package com.gustavoreinaldi.tokiomarine.useCases;

import java.time.LocalDate;

public interface TransferCalculationUseCase {
    Double calculateTransferValueWithTaxAdded(LocalDate targetDate, double amountToTransfer);
}
