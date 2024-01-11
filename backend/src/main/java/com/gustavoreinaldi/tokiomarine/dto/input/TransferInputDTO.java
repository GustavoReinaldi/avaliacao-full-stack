package com.gustavoreinaldi.tokiomarine.dto.input;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.Date;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransferInputDTO{
    @NotNull
    @Length(min = 6, max = 6, message = "Deve totalizar 6 dígitos")
    private String destinationAccount;
    @NotNull
    @Length(min = 6, max = 6, message = "Deve totalizar 6 dígitos")
    private String originAccount;
    @NotNull
    private LocalDate scheduledDate;
    @NotNull
    private Double transferAmount;
}
