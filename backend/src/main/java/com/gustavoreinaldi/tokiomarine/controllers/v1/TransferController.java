package com.gustavoreinaldi.tokiomarine.controllers.v1;

import com.gustavoreinaldi.tokiomarine.dto.TransferDTO;
import com.gustavoreinaldi.tokiomarine.dto.input.TransferInputDTO;
import com.gustavoreinaldi.tokiomarine.services.CreateTransferenceService;
import com.gustavoreinaldi.tokiomarine.services.DeleteTransferByIdService;
import com.gustavoreinaldi.tokiomarine.services.ListAllTransfers;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/transfer")
public class TransferController {
    private CreateTransferenceService createTransferenceService;
    private ListAllTransfers listAllTransfers;
    private DeleteTransferByIdService deleteTransferByIdService;

    @Autowired
    public TransferController(CreateTransferenceService createTransferenceService,
                              ListAllTransfers listAllTransfers,
                              DeleteTransferByIdService deleteTransferByIdService) {
        this.createTransferenceService = createTransferenceService;
        this.listAllTransfers = listAllTransfers;
        this.deleteTransferByIdService = deleteTransferByIdService;
    }

    @PostMapping
    ResponseEntity<?> doTransfer(@RequestBody @Valid TransferInputDTO input) {
        createTransferenceService.doTransfer(input);
        return ResponseEntity.ok().build();
    }
    @GetMapping
    ResponseEntity<List<TransferDTO>> listAll () {
        return ResponseEntity.ok(listAllTransfers.listAllTransfers());
    }
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteById (@PathVariable("id") String id){
        deleteTransferByIdService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
