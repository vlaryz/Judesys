import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {EventsResponse} from "../../models/eventsResponse";
import {EventsService} from "../../services/events.service";
import {TicketsService} from "../../services/tickets.service";
import {ActivatedRoute, Router} from "@angular/router";
import {CitiesService} from "../../services/cities.service";
import {CityResponse} from "../../models/cityResponse";
import {TicketResponse} from "../../models/ticketResponse";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {tick} from "@angular/core/testing";

@Component({
  selector: 'app-ticket-purchase',
  templateUrl: './ticket-purchase.component.html',
  styleUrls: ['./ticket-purchase.component.css']
})
export class TicketPurchaseComponent implements OnInit {
  public event: EventsResponse = {} as EventsResponse;
  public city: CityResponse = {} as CityResponse;
  public tickets: TicketResponse[] = [];
  public vipTickets: TicketResponse[] = [];
  public basicTickets: TicketResponse[] = [];
  public selectedType: string = "";
  public buyTicketForm = new FormGroup({
    type: new FormControl<string>({ value: '', disabled: false }, Validators.required),
    count: new FormControl<string>({ value: '1', disabled: false }, Validators.required)
  });
  public selectedTypeTotal = <FormControl>(this.buyTicketForm.get('type'));
  public selectedCountTotal = <FormControl>(this.buyTicketForm.get('count'));
  public total: number = 0;

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: {event: EventsResponse, cityid: string, eventid: string},
    private eventService: EventsService,
    private citiesService: CitiesService,
    private ticketsService: TicketsService,
    ) { }

  ngOnInit(): void {
    this.event = this.data.event;
    const cityId = this.data.cityid;
    const eventId = this.data.eventid;

    this.eventService.getEvent(cityId, eventId).subscribe(x => {
      this.event = x;
    })

    this.citiesService.getCity(cityId).subscribe(x => {
      this.city = x;
    })

    this.ticketsService.getEventTickets(cityId, eventId).subscribe(x => {
      this.tickets = x;
      this.vipTickets = this.tickets.filter(t => t.type == 'VIP').sort(t => +t.price);
      this.basicTickets = this.tickets.filter(t => t.type == 'BASIC').sort(t => +t.price);
    })

    this.selectedCountTotal.valueChanges.subscribe(data => {
      this.total = +this.buyTicketForm.get('count')!.value! * (this.buyTicketForm.get('type')!.value == 'VIP' ? +this.vipTickets[0].price : +this.basicTickets[0].price);
    });

    this.selectedTypeTotal.valueChanges.subscribe(data => {
      this.total = +this.buyTicketForm.get('count')!.value! * (this.buyTicketForm.get('type')!.value == 'VIP' ? +this.vipTickets[0].price : +this.basicTickets[0].price);
    });
  }

  public buyTicket(): void {
    const ticket = {} as TicketResponse;
    ticket.type = this.buyTicketForm.get('type')!.value!;
    ticket.price = (this.buyTicketForm.get('type')!.value == 'VIP' ? this.vipTickets[0].price : this.basicTickets[0].price);

    for(let i = 0; i < +this.buyTicketForm.get('count')!.value!; i++)
      this.ticketsService.addTicket(this.city.id, this.event.id, ticket).subscribe(x => {
        console.log(x);
      });


    console.log(this.buyTicketForm.get("type")?.value);
    console.log(this.buyTicketForm.get("count")?.value);
  }

}
