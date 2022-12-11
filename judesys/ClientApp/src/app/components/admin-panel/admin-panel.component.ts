import { Component, OnInit } from '@angular/core';
import {CityResponse} from "../../models/cityResponse";
import {EventsResponse} from "../../models/eventsResponse";
import {TicketResponse} from "../../models/ticketResponse";
import {EventsService} from "../../services/events.service";
import {CitiesService} from "../../services/cities.service";
import {TicketsService} from "../../services/tickets.service";
import {EventDetailsComponent} from "../event-details/event-details.component";
import {MatDialog} from "@angular/material/dialog";
import {CityEditComponent} from "../city-edit/city-edit.component";
import {EventEditComponent} from "../event-edit/event-edit.component";
import {TicketEditComponent} from "../ticket-edit/ticket-edit.component";

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  styleUrls: ['./admin-panel.component.css']
})
export class AdminPanelComponent implements OnInit {

  public allCities: CityResponse[] = [];
  public allEvents: EventsResponse[] = [];
  public allTickets: TicketResponse[] = [];

  public selectedCity: CityResponse = {} as CityResponse;
  public selectedEvent: EventsResponse = {} as EventsResponse;
  public selectedTicket: TicketResponse = {} as TicketResponse;


  constructor(
    private eventService: EventsService,
    private citiesService: CitiesService,
    private ticketsService: TicketsService,
    public dialog: MatDialog
    ) { }

  ngOnInit(): void {
    this.citiesService.getCities().subscribe(x => {
      this.allCities = x;
    })

  }

  public handleTabClick(): void {
    this.eventService.getAllCityEvents(this.selectedCity.id).subscribe(x => {
      this.allEvents = x;
    });

    this.ticketsService.getEventTickets(this.selectedCity.id, this.selectedEvent.id).subscribe(x => {
      this.allTickets = x;
    });
  }

  // --------------------------- Cities ---------------------------
  deleteCity(): void {
    this.citiesService.deleteCity(this.selectedCity.id).subscribe(x => {
      console.log(x);
    })
  }

  openCityEditDialog(city: CityResponse) {
    console.log(JSON.stringify(city));
    const dialogRef = this.dialog.open(CityEditComponent,
      {
        data: {act: "edit", city: city},
        width: '80%',
      });

    dialogRef.afterClosed().subscribe(_ => {
    })
  }

  openAddEditDialog(city: CityResponse) {
    console.log(JSON.stringify(city));
    const dialogRef = this.dialog.open(CityEditComponent,
      {
        data: {act: "add", city: city},
        width: '80%',
      });

    dialogRef.afterClosed().subscribe(_ => {
    })
  }

  // --------------------------- Events ---------------------------
  deleteEvent(): void {
    this.eventService.deleteEvent(this.selectedCity.id, this.selectedEvent.id).subscribe(x => {
      console.log(x);
    })
  }

  openEventEditDialog(event: EventsResponse) {
    // console.log(JSON.stringify(event));
    const dialogRef = this.dialog.open(EventEditComponent,
      {
        data: {act: "edit", cityId: this.selectedCity.id, event: event},
        width: '80%',
      });

    dialogRef.afterClosed().subscribe(_ => {
    })
  }

  openAddEventDialog(event: EventsResponse) {
    // console.log(JSON.stringify(city));
    const dialogRef = this.dialog.open(EventEditComponent,
      {
        data: {act: "add", cityId: this.selectedCity.id, event: event},
        width: '80%',
      });

    dialogRef.afterClosed().subscribe(_ => {
    })
  }

  // --------------------------- Tickets ---------------------------
  deleteTicket(): void {
    this.ticketsService.deleteTicket(this.selectedCity.id, this.selectedEvent.id, this.selectedTicket.id).subscribe(x => {
      console.log(x);
    })
  }

  openTicketEditDialog(ticket: TicketResponse) {
    // console.log(JSON.stringify(event));
    const dialogRef = this.dialog.open(TicketEditComponent,
      {
        data: {act: "edit", cityId: this.selectedCity.id, eventId: this.selectedTicket.id, ticket: ticket},
        width: '80%',
      });

    dialogRef.afterClosed().subscribe(_ => {
    })
  }

  openAddTicketDialog(ticket: TicketResponse) {
    // console.log(JSON.stringify(city));
    const dialogRef = this.dialog.open(TicketEditComponent,
      {
        data: {act: "add", cityId: this.selectedCity.id, eventId: this.selectedTicket.id, ticket: ticket},
        width: '80%',
      });

    dialogRef.afterClosed().subscribe(_ => {
    })
  }

}
