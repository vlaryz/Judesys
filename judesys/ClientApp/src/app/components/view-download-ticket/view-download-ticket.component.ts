import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {CityResponse} from "../../models/cityResponse";
import {ActivatedRoute, Router} from "@angular/router";
import {EventsService} from "../../services/events.service";
import {CitiesService} from "../../services/cities.service";
import {TicketsService} from "../../services/tickets.service";
import {TicketResponse} from "../../models/ticketResponse";

@Component({
  selector: 'app-view-download-ticket',
  templateUrl: './view-download-ticket.component.html',
  styleUrls: ['./view-download-ticket.component.css']
})
export class ViewDownloadTicketComponent implements OnInit {

  public ticket: TicketResponse = {} as TicketResponse;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private eventsService: EventsService,
    private cityService: CitiesService,
    private ticketService: TicketsService
  ) { }

  ngOnInit(): void {
    const cityId = this.route.snapshot.paramMap.get('id1')!;
    const eventId = this.route.snapshot.paramMap.get('id2')!;
    const ticketId = this.route.snapshot.paramMap.get('id3')!;

    this.ticketService.getTicket(cityId, eventId, ticketId).subscribe(x => {
      this.ticket = x;
    })
    // console.log(JSON.stringify(this.data));
  }

}
