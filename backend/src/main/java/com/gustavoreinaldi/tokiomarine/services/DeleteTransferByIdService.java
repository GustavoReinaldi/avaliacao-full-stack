package com.gustavoreinaldi.tokiomarine.services;

import com.gustavoreinaldi.tokiomarine.repositories.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service("DeleteTransferByIdService")
public class DeleteTransferByIdService {
    private TransferRepository transferRepository;

    @Autowired
    public DeleteTransferByIdService(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }
    @Transactional
    public void deleteById(String stringId){
        var id = UUID.fromString(stringId);
        transferRepository.deleteById(id);
    }
}
