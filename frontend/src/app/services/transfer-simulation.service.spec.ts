import { TestBed } from '@angular/core/testing';

import { TransferSimulationService } from './transfer-simulation.service';

describe('TransferSimulationService', () => {
  let service: TransferSimulationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TransferSimulationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
