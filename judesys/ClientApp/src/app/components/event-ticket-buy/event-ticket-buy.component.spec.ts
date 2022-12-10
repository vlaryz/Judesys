import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EventTicketBuyComponent } from './event-ticket-buy.component';

describe('EventTicketBuyComponent', () => {
  let component: EventTicketBuyComponent;
  let fixture: ComponentFixture<EventTicketBuyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EventTicketBuyComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EventTicketBuyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
