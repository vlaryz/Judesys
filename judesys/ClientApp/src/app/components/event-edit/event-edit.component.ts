import {Component, Inject, OnInit} from '@angular/core';
import {CityResponse} from "../../models/cityResponse";
import {FormControl, FormGroup} from "@angular/forms";
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {CitiesService} from "../../services/cities.service";
import {EventsResponse} from "../../models/eventsResponse";
import {EventsService} from "../../services/events.service";
import {EventsResponseFormatted} from "../../models/eventsResponseFormatted";

@Component({
  selector: 'app-event-edit',
  templateUrl: './event-edit.component.html',
  styleUrls: ['./event-edit.component.css']
})
export class EventEditComponent implements OnInit {

  public event: EventsResponse = {} as EventsResponse;
  public cityId: string = "";
  public isFree: boolean = true;

  public eventFormGroup = new FormGroup({
    name: new FormControl<string>(''),
    isFree: new FormControl<boolean>(true),
    shortDescription: new FormControl<string>(''),
    longDescription: new FormControl<string>('')
  });

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: { act: string, cityId: string, event: EventsResponse },
    private citiesService: CitiesService,
    private eventsService: EventsService
  ) { }

  ngOnInit(): void {
    this.event = this.data.event;
    this.cityId = this.data.cityId;
    console.log(JSON.stringify(this.data));


    this.eventFormGroup.setValue({
      name: this.event.name!,
      isFree:  this.event.free!,
      shortDescription: this.event.shortDescr!,
      longDescription: this.event.longDescr!
    });
  }

  public saveEvent(): void {
    const event = {
      name: this.eventFormGroup.get('name')!.value,
      isFree: this.isFree,
      shortDescription: this.eventFormGroup.get('shortDescription')!.value,
      longDescription: this.eventFormGroup.get('longDescription')!.value!
    }
    if(this.data.act == "edit") {
      this.eventsService.editEvent(this.cityId, this.event.id, event as EventsResponseFormatted).subscribe(x => {
        console.log(x);
      })
    }
    else {
      this.eventsService.addEvent(this.cityId, event as EventsResponseFormatted).subscribe(x => {
        console.log(x);
      })
    }
  }

}
