import {Component, Inject, OnInit} from '@angular/core';
import {EventsResponse} from "../../models/eventsResponse";
import {FormControl, FormGroup} from "@angular/forms";
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {CitiesService} from "../../services/cities.service";
import {EventsService} from "../../services/events.service";
import {EventsResponseFormatted} from "../../models/eventsResponseFormatted";
import {TicketResponse} from "../../models/ticketResponse";
import {TicketsService} from "../../services/tickets.service";

@Component({
  selector: 'app-ticket-edit',
  templateUrl: './ticket-edit.component.html',
  styleUrls: ['./ticket-edit.component.css']
})
export class TicketEditComponent implements OnInit {

  public ticket: TicketResponse = {} as TicketResponse;
  public cityId: string = "";
  public eventId: string = "";
  public type: string = "";

  public ticketFormGroup = new FormGroup({
    price: new FormControl<string>(''),
    type: new FormControl<string>('VIP'),
  });

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: { act: string, cityId: string, eventId: string, ticket: TicketResponse },
    private citiesService: CitiesService,
    private eventsService: EventsService,
    private ticketService: TicketsService
  ) { }

  ngOnInit(): void {
    this.eventId = this.data.eventId;
    this.cityId = this.data.cityId;
    this.ticket = this.data.ticket;
    console.log(JSON.stringify(this.data));


    this.ticketFormGroup.setValue({
      price: this.ticket.price,
      type:  this.ticket.type!,
    });
  }

  public saveTicket(): void {
    console.log("TYPE " + this.eventId);
    console.log("TYPE " + this.ticketFormGroup.get('type')!.value);
    const ticket = {
      price: this.ticketFormGroup.get('price')!.value,
      type: this.ticketFormGroup.get('type')!.value
    }

    if(this.data.act == "edit") {
      this.ticketService.editTicket(this.cityId, this.eventId, this.ticket.id, ticket as TicketResponse).subscribe(x => {
        console.log(x);
      })
    }
    else {
      this.ticketService.addTicket(this.cityId, this.eventId, ticket as TicketResponse).subscribe(x => {
        console.log(x);
      })
    }
  }

}
