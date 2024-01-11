package com.gustavoreinaldi.tokiomarine.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransferDTO {
    private UUID transferId;
    private String destinationAccount;
    private String originAccount;
    private String scheduledDate;
    private String transferDate;
    private Double transferAmount;
    private Double transferTax;
}
