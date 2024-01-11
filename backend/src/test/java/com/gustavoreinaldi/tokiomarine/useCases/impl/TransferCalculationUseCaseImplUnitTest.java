package com.gustavoreinaldi.tokiomarine.useCases.impl;

import com.gustavoreinaldi.tokiomarine.exceptions.InvalidArgumentsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
class TransferCalculationUseCaseImplUnitTest {
    @InjectMocks
    private TransferCalculationUseCaseImpl useCase;

    @Test
    public void transferInSameDayWhenSuccess(){
        LocalDate dateToTransfer = LocalDate.of(2024,1,12);
        LocalDate MOCKED_NOW_DATE = LocalDate.of(2024, 1, 12);
        // Mocks the Local Date
        try (MockedStatic<LocalDate> topDateTimeUtilMock = Mockito.mockStatic(LocalDate.class)) {
            topDateTimeUtilMock.when(() -> LocalDate.now()).thenReturn(MOCKED_NOW_DATE);
            assertAll(
                    ()-> assertDoesNotThrow(() -> useCase
                            .calculateTransferValueWithTaxAdded(dateToTransfer, 1000)),
                    ()-> assertEquals(106, useCase
                            .calculateTransferValueWithTaxAdded(dateToTransfer, 100)),
                    ()-> assertThrows(InvalidArgumentsException.class, () -> useCase
                            .calculateTransferValueWithTaxAdded(dateToTransfer, 1001))
            );
        }
    }

    @Test
    public void transferInNextDayWhenSuccess(){
        LocalDate dateToTransfer = LocalDate.of(2024,1,13);
        LocalDate MOCKED_NOW_DATE = LocalDate.of(2024, 1, 12);
        // Mocks the Local Date
        try (MockedStatic<LocalDate> topDateTimeUtilMock = Mockito.mockStatic(LocalDate.class)) {
            topDateTimeUtilMock.when(() -> LocalDate.now()).thenReturn(MOCKED_NOW_DATE);
            assertAll(
                    ()-> assertDoesNotThrow(() -> useCase
                            .calculateTransferValueWithTaxAdded(dateToTransfer, 2000)),
                    ()-> assertEquals(2012, useCase
                            .calculateTransferValueWithTaxAdded(dateToTransfer, 2000)),
                    ()-> assertThrows(InvalidArgumentsException.class, () -> useCase
                            .calculateTransferValueWithTaxAdded(dateToTransfer, 2001))
            );
        }
    }
    @Test
    public void transferIn10DaysAheadWhenSuccessTax082percent(){
        LocalDate dateToTransfer = LocalDate.of(2024,1,23);
        LocalDate wrongDate = LocalDate.of(2024,1,22);
        LocalDate MOCKED_NOW_DATE = LocalDate.of(2024, 1, 12);
        // Mocks the Local Date
        try (MockedStatic<LocalDate> topDateTimeUtilMock = Mockito.mockStatic(LocalDate.class)) {
            topDateTimeUtilMock.when(() -> LocalDate.now()).thenReturn(MOCKED_NOW_DATE);
            assertAll(
                    ()-> assertDoesNotThrow(() -> useCase
                            .calculateTransferValueWithTaxAdded(dateToTransfer, 2500)),
                    ()-> assertEquals(2705, useCase
                            .calculateTransferValueWithTaxAdded(dateToTransfer, 2500)),
                    ()-> assertThrows(InvalidArgumentsException.class, () -> useCase
                            .calculateTransferValueWithTaxAdded(wrongDate, 2500)),
                    ()-> assertThrows(InvalidArgumentsException.class, () -> useCase
                            .calculateTransferValueWithTaxAdded(dateToTransfer, 2000))
            );
        }
    }
    @Test
    public void transferIn10DaysAheadWhenSuccessTax069percent(){
        LocalDate dateToTransfer = LocalDate.of(2024,2,2);
        LocalDate wrongDate = LocalDate.of(2024,1,31);
        LocalDate MOCKED_NOW_DATE = LocalDate.of(2024, 1, 12);
        // Mocks the Local Date
        try (MockedStatic<LocalDate> topDateTimeUtilMock = Mockito.mockStatic(LocalDate.class)) {
            topDateTimeUtilMock.when(() -> LocalDate.now()).thenReturn(MOCKED_NOW_DATE);
            assertAll(
                    ()-> assertDoesNotThrow(() -> useCase
                            .calculateTransferValueWithTaxAdded(dateToTransfer, 2500)),
                    ()-> assertEquals(2672.5, useCase
                            .calculateTransferValueWithTaxAdded(dateToTransfer, 2500)),
                    ()-> assertNotEquals(2672.5, useCase
                            .calculateTransferValueWithTaxAdded(wrongDate, 2500))
            );
        }
    }

    @Test
    public void transferIn10DaysAheadWhenSuccessTax047percent(){
        LocalDate dateToTransfer = LocalDate.of(2024,2,12);
        LocalDate wrongDate = LocalDate.of(2024,2,11);
        LocalDate MOCKED_NOW_DATE = LocalDate.of(2024, 1, 12);
        // Mocks the Local Date
        try (MockedStatic<LocalDate> topDateTimeUtilMock = Mockito.mockStatic(LocalDate.class)) {
            topDateTimeUtilMock.when(() -> LocalDate.now()).thenReturn(MOCKED_NOW_DATE);
            assertAll(
                    ()-> assertDoesNotThrow(() -> useCase
                            .calculateTransferValueWithTaxAdded(dateToTransfer, 2500)),
                    ()-> assertEquals(2617.5, useCase
                            .calculateTransferValueWithTaxAdded(dateToTransfer, 2500)),
                    ()-> assertNotEquals(2617.5, useCase
                            .calculateTransferValueWithTaxAdded(wrongDate, 2500))
            );
        }
    }
    @Test
    public void transferIn10DaysAheadWhenSuccessTax017percent(){
        LocalDate dateToTransfer = LocalDate.of(2024,2,22);
        LocalDate wrongDate = LocalDate.of(2024,2,21);
        LocalDate MOCKED_NOW_DATE = LocalDate.of(2024, 1, 12);
        // Mocks the Local Date
        try (MockedStatic<LocalDate> topDateTimeUtilMock = Mockito.mockStatic(LocalDate.class)) {
            topDateTimeUtilMock.when(() -> LocalDate.now()).thenReturn(MOCKED_NOW_DATE);
            assertAll(
                    ()-> assertDoesNotThrow(() -> useCase
                            .calculateTransferValueWithTaxAdded(dateToTransfer, 3400)),
                    ()-> assertEquals(3457.8, useCase
                            .calculateTransferValueWithTaxAdded(dateToTransfer, 3400)),
                    ()-> assertNotEquals(3457.8, useCase
                            .calculateTransferValueWithTaxAdded(wrongDate, 3400))
            );
        }
    }
}