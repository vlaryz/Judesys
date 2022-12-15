import { Component, OnInit } from '@angular/core';
import {AuthorizationService} from "../../services/authorization.service";
import {TicketsService} from "../../services/tickets.service";
import jwt_decode from "jwt-decode";
import {JwtToken} from "../../models/jwtToken";
import {TicketResponse} from "../../models/ticketResponse";
import {TicketEditComponent} from "../ticket-edit/ticket-edit.component";
import {EventsService} from "../../services/events.service";
import {CitiesService} from "../../services/cities.service";
import {MatDialog} from "@angular/material/dialog";
import {ViewDownloadTicketComponent} from "../view-download-ticket/view-download-ticket.component";

@Component({
  selector: 'app-account-details',
  templateUrl: './account-details.component.html',
  styleUrls: ['./account-details.component.css']
})
export class AccountDetailsComponent implements OnInit {

  public userName: string = "";
  public userRole: string = "";
  public boughtTickets: TicketResponse[] = [];
  public displayedColumns: string[] = ['City', 'Event', 'Type', 'Price', 'Actions'];
  constructor(
    private authService: AuthorizationService,
    private ticketService: TicketsService,
    private eventService: EventsService,
    private citiesService: CitiesService,
    public dialog: MatDialog
  ) { }

  ngOnInit(): void {

    try {
      this.userName = (jwt_decode(this.authService.jwtToken) as JwtToken).sub;
      this.userRole = (jwt_decode(this.authService.jwtToken) as JwtToken).roles;
    }
    catch (e) {
    }

    this.ticketService.getUserTickets('0', '0').subscribe(x => {
      console.log(x);
      this.boughtTickets = x;
    })
  }

  openTicket(ticket: TicketResponse) {
    // console.log(JSON.stringify(event));
    const dialogRef = this.dialog.open(ViewDownloadTicketComponent,
      {
        data: ticket,
        width: '80%',
      });

    dialogRef.afterClosed().subscribe(_ => {
    })
  }

}
