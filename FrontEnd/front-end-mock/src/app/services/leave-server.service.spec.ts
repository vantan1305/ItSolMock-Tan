import { TestBed } from '@angular/core/testing';

import { LeaveServerService } from './leave-server.service';

describe('LeaveServerService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: LeaveServerService = TestBed.get(LeaveServerService);
    expect(service).toBeTruthy();
  });
});
