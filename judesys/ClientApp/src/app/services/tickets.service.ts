import { Injectable } from '@angular/core';
import { HttpService } from "./http.service";
import { Observable } from 'rxjs';
import {CityResponse} from "../models/cityResponse";
import {EventsResponse} from "../models/eventsResponse";
import {TicketResponse} from "../models/ticketResponse";

@Injectable({
  providedIn: 'root'
})

export class TicketsService {

  constructor(
    private httpService: HttpService
  ) {
  }

  public getEventTickets(cityId: string, eventId: string): Observable<TicketResponse[]> {
    return this.httpService.get('/api/cities/' + cityId + '/events/' + eventId , '/tickets/');
  }

}
