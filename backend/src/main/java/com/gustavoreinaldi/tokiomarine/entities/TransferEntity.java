package com.gustavoreinaldi.tokiomarine.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "transfer")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idtran_tra", columnDefinition = "uuid")
    private UUID transferId;
    @Column(name = "desacc_tra")
    private String destinationAccount;
    @Column(name = "oriacc_tra")
    private String originAccount;
    @Column(name = "datsch_tra")
    private LocalDate scheduledDate;
    @Column(name = "dattra_tra")
    private LocalDate transferDate;
    @Column(name = "amotra_tra")
    private Double transferAmount;
    @Column(name = "taxtra_tra")
    private Double transferTax;
}
