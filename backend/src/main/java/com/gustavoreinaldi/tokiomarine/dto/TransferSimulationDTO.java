package com.gustavoreinaldi.tokiomarine.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferSimulationDTO {
    private Double valueToTransfer;
    private Double taxesTransfer;
    private Double subTotal;
}
