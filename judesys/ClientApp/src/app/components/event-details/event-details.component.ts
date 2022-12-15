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
  public cityName: string = "";
  public event: EventsResponse = {} as EventsResponse;

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: {event: EventsResponse, city: string},
  ) { }

  ngOnInit(): void {
    this.eventDescription = this.event.longDescr;
    this.event = this.data.event;
    this.price = String(this.data.event.free);
    this.cityName = this.data.city;
  }

}
