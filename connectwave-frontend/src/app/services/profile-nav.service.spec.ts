import { TestBed } from '@angular/core/testing';

import { ProfileNavService } from './profile-nav.service';

describe('ProfileNavService', () => {
  let service: ProfileNavService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProfileNavService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
