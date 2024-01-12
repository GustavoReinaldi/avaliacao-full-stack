package com.gustavoreinaldi.tokiomarine.useCases.impl;

import com.gustavoreinaldi.tokiomarine.exceptions.InvalidArgumentsException;
import com.gustavoreinaldi.tokiomarine.useCases.TransferCalculationUseCase;
import com.gustavoreinaldi.tokiomarine.utils.NumberUtils;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component("TransferCalculationUseCaseImplV1")
public class TransferCalculationUseCaseImpl implements TransferCalculationUseCase {

    @Override
    public Double calculateTransferValueWithTaxAdded(LocalDate targetLocalDate, double amountToTransfer) {
        Date targetDate = Date.from(targetLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        assertDateNotNull(targetDate);
        long differenceOfDays = getDifferenceBetweenTodayAndTargetDate(targetDate);
        double valueTaxed = 0.0;

        if(amountToTransfer > 0 && amountToTransfer <=1000) valueTaxed = calculateTaxTypeA(differenceOfDays, amountToTransfer);
        else if(amountToTransfer <= 2000) valueTaxed = calculateTaxTypeB(differenceOfDays, amountToTransfer);
        else if(amountToTransfer > 2000) valueTaxed = calculateTaxTypeC(differenceOfDays, amountToTransfer);
        else throwsInvalidValuesException();

        return NumberUtils.twoDecimalsOnly(valueTaxed);
    }

    private Double calculateTaxTypeA(long differenceOfDays, double amountToTax){
        if(differenceOfDays != 0) throwsInvalidValuesException();

        return amountToTax * 1.03 + 3;
    }
    private Double calculateTaxTypeB(long differenceOfDays, double amountToTax){
        if(differenceOfDays < 1 || differenceOfDays > 10) throwsInvalidValuesException();

        return amountToTax + 12;
    }
    private Double calculateTaxTypeC(long differenceOfDays, double amountToTax){
        double taxPercent = 0.0;
        if(differenceOfDays <= 10) throwsInvalidValuesException();

        else if(differenceOfDays <= 20 ) taxPercent = 0.082;
        else if(differenceOfDays <= 30 ) taxPercent = 0.069;
        else if(differenceOfDays <= 40 ) taxPercent = 0.047;
        else taxPercent = 0.017;
        return amountToTax * (1 + taxPercent);
    }

    private long getDifferenceBetweenTodayAndTargetDate(Date targetDate) {
        Date today = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        long diffInMillies = Math.abs(targetDate.getTime() - today.getTime());
        return TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    private void assertDateNotNull (Date targetDate) {
        if(targetDate == null) throw new InvalidArgumentsException("Você deve preencher a data de agendameto");
    }
    private void throwsInvalidValuesException() {
        throw new InvalidArgumentsException("Erro: essa transferência não tem taxas previstas.");
    }
}

