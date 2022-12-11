import { Injectable } from '@angular/core';
import { HttpService } from "./http.service";
import { Observable } from 'rxjs';
import {CityResponse} from "../models/cityResponse";
import {EventsResponse} from "../models/eventsResponse";
import * as events from "events";
import {EventsResponseFormatted} from "../models/eventsResponseFormatted";

@Injectable({
  providedIn: 'root'
})

export class EventsService {

  constructor(
    private httpService: HttpService
  ) {
  }

  public getAllCityEvents(cityId: string): Observable<EventsResponse[]> {
    return this.httpService.get('/api/cities/' + cityId, '/events/');
  }

  public getEvent(cityId: string, eventId: string): Observable<EventsResponse> {
    return this.httpService.get('/api/cities/' + cityId, '/events/' + eventId);
  }

  public deleteEvent(id: string, eventid: string): Observable<CityResponse> {
    return this.httpService.delete('/api/cities/' + id, '/events/' + eventid);
  }

  public editEvent(id: string, eventid: string, event: EventsResponseFormatted): Observable<CityResponse> {
    return this.httpService.put('/api/cities/' + id, '/events/' + eventid, event);
  }

  public addEvent(cityId: string, event: EventsResponseFormatted): Observable<CityResponse> {
    return this.httpService.post('/api/cities/' + cityId, '/events/', event);
  }
}
