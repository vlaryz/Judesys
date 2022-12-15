import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewDownloadTicketComponent } from './view-download-ticket.component';

describe('ViewDownloadTicketComponent', () => {
  let component: ViewDownloadTicketComponent;
  let fixture: ComponentFixture<ViewDownloadTicketComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewDownloadTicketComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewDownloadTicketComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
