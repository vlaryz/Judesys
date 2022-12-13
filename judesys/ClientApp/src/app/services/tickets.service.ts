import { Injectable } from '@angular/core';
import { HttpService } from "./http.service";
import { Observable } from 'rxjs';
import {CityResponse} from "../models/cityResponse";
import {EventsResponse} from "../models/eventsResponse";
import {TicketResponse} from "../models/ticketResponse";
import {EventsResponseFormatted} from "../models/eventsResponseFormatted";
import {HttpHeaders, HttpParams} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})

export class TicketsService {

  constructor(
    private httpService: HttpService
  ) {
  }

  public getEventTickets(cityId: string, eventId: string): Observable<TicketResponse[]> {
    return this.httpService.get('/api/cities/' + cityId + '/events/' + eventId, '/tickets/');
  }

  public deleteTicket(id: string, eventid: string, ticketid: string): Observable<TicketResponse> {
    return this.httpService.delete('/api/cities/' + id, '/events/' + eventid + '/tickets/' + ticketid);
  }

  public editTicket(id: string, eventid: string, ticketid: string, ticket: TicketResponse): Observable<TicketResponse> {
    return this.httpService.put('/api/cities/' + id, '/events/' + eventid + '/tickets/' + ticketid, ticket);
  }

  public addTicket(id: string, eventid: string, ticket: TicketResponse): Observable<TicketResponse> {
    return this.httpService.post('/api/cities/' + id, '/events/' + eventid + '/tickets/', ticket);
  }

  public getUserTickets(cityId: string, eventId: string): Observable<TicketResponse[]> {
    return this.httpService.get('/api/cities/' + cityId + '/events/' + eventId, '/tickets/byUser');
  }
}
