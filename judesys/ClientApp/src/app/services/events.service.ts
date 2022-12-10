import { Injectable } from '@angular/core';
import { HttpService } from "./http.service";
import { Observable } from 'rxjs';
import {CityResponse} from "../models/cityResponse";
import {EventsResponse} from "../models/eventsResponse";

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
}
