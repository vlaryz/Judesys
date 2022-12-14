import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {EventsService} from "../../services/events.service";
import {EventsResponse} from "../../models/eventsResponse";
import {CitiesService} from "../../services/cities.service";
import {CityResponse} from "../../models/cityResponse";
import {MatDialog} from '@angular/material/dialog';
import {EventDetailsComponent} from "../event-details/event-details.component";

@Component({
  selector: 'app-city-events',
  templateUrl: './city-events.component.html',
  styleUrls: ['./city-events.component.css']
})
export class CityEventsComponent implements OnInit {

  public events: EventsResponse[] = [];
  public city = "";
  public cityId: string = "";

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private eventsService: EventsService,
    private cityService: CitiesService,
    public dialog: MatDialog
  ) { }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id')!;
    this.cityId = id;
    // console.log("IDDD: " + id);
    this.cityService.getCity(id).subscribe(x => {
      this.city = x.name;
    })
    this.eventsService.getAllCityEvents(id).subscribe(x => {
      this.events = x;
      // console.log("events: " + JSON.stringify(x));
      // this.events.forEach(x => {
      //     console.log(x.name + " " + x.isFree)
      // })
    })
  }

  openDialog(event: EventsResponse) {
    const dialogRef = this.dialog.open(EventDetailsComponent,
      {
        data: {event: event, city: this.city},
        width: '80%',
      });

    dialogRef.afterClosed().subscribe(_ => {
    })
  }

}
