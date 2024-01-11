package com.gustavoreinaldi.tokiomarine.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gustavoreinaldi.tokiomarine.dto.TransferDTO;
import com.gustavoreinaldi.tokiomarine.entities.TransferEntity;
import com.gustavoreinaldi.tokiomarine.repositories.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("ListAllTransfersV1")
public class ListAllTransfers {
    private TransferRepository transferRepository;

    @Autowired
    public ListAllTransfers(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    public List<TransferDTO> listAllTransfers() {
        ObjectMapper objMap = new ObjectMapper();
        List<TransferEntity> allTransfers = transferRepository.getAllOrderByScheduledDate();

        return allTransfers.stream()
                .map(item -> TransferDTO.builder()
                        .transferId(item.getTransferId())
                        .transferDate(item.getTransferDate().toString())
                        .scheduledDate(item.getScheduledDate().toString())
                        .transferTax(item.getTransferTax())
                        .transferAmount(item.getTransferAmount())
                        .destinationAccount(item.getDestinationAccount())
                        .originAccount(item.getOriginAccount())
                        .build())
                .collect(Collectors.toList());
    }
}
