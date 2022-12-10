import {Component, Inject, OnInit} from '@angular/core';
import {EventsService} from "../../services/events.service";
import {EventsResponse} from "../../models/eventsResponse";
import {MAT_DIALOG_DATA} from "@angular/material/dialog";

@Component({
  selector: 'app-event-details',
  templateUrl: './event-details.component.html',
  styleUrls: ['./event-details.component.css']
})
export class EventDetailsComponent implements OnInit {

  public eventDescription: string = "";
  private price: string = "";
  public event: EventsResponse = {} as EventsResponse;

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: EventsResponse,
  ) { }

  ngOnInit(): void {
    this.eventDescription = this.data.name;
    this.event = this.data;
    this.price = String(this.data.free);
  }

}
