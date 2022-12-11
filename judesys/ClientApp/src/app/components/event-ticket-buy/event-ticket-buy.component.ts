import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {EventsResponse} from "../../models/eventsResponse";
import {EventsService} from "../../services/events.service";
import {CitiesService} from "../../services/cities.service";
import {CityResponse} from "../../models/cityResponse";
import {TicketsService} from "../../services/tickets.service";
import {TicketResponse} from "../../models/ticketResponse";
import {MatDialog} from "@angular/material/dialog";
import {EventDetailsComponent} from "../event-details/event-details.component";
import {TicketPurchaseComponent} from "../ticket-purchase/ticket-purchase.component";

@Component({
  selector: 'app-event-ticket-buy',
  templateUrl: './event-ticket-buy.component.html',
  styleUrls: ['./event-ticket-buy.component.css']
})
export class EventTicketBuyComponent implements OnInit {

  public event: EventsResponse = {} as EventsResponse;
  public city: CityResponse = {} as CityResponse;
  public tickets: TicketResponse[] = [];
  public vipTickets: TicketResponse[] = [];
  public basicTickets: TicketResponse[] = [];
  public cityid: string = "";
  public eventid: string = "";


  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private eventService: EventsService,
    private citiesService: CitiesService,
    private ticketsService: TicketsService,
    public dialog: MatDialog
  ) { }

  ngOnInit(): void {
    const cityId = this.route.snapshot.paramMap.get('id1')!;
    const eventId = this.route.snapshot.paramMap.get('id2')!;
    this.cityid = cityId;
    this.eventid = eventId;
    this.eventService.getEvent(cityId, eventId).subscribe(x => {
      this.event = x;
      // console.log(this.event)
    })

    this.citiesService.getCity(cityId).subscribe(x => {
      this.city = x;
    })

    this.ticketsService.getEventTickets(cityId, eventId).subscribe(x => {
      this.tickets = x;
      // console.log(JSON.stringify(x));
      this.vipTickets = this.tickets.filter(t => t.type == 'VIP').sort(t => +t.price);
      this.basicTickets = this.tickets.filter(t => t.type == 'BASIC').sort(t => +t.price);
    })
  }

  openDialog(event: EventsResponse) {
    const dialogRef = this.dialog.open(TicketPurchaseComponent,
      {
        data: {event: event, cityid: this.cityid, eventid: this.eventid},
        width: '80%',
      });

    dialogRef.afterClosed().subscribe(_ => {
      // console.log("fin");
    })
  }

}
