import { Injectable } from '@angular/core';
import { HttpService } from "./http.service";
import { Observable } from 'rxjs';
import {CityResponse} from "../models/cityResponse";

@Injectable({
  providedIn: 'root'
})

export class CitiesService {

  constructor(
    private httpService: HttpService
  ) {
  }

  public getCities(): Observable<CityResponse[]> {
    return this.httpService.get('/api/cities', '/');
  }

  public getCity(id: string): Observable<CityResponse> {
    return this.httpService.get('/api/cities/' + id, '');
  }

  public deleteCity(id: string): Observable<CityResponse> {
    return this.httpService.delete('/api/cities/' + id, '');
  }

  public editCity(id: string, city: CityResponse): Observable<CityResponse> {
    return this.httpService.put('/api/cities/' + id, '', city);
  }

  public addCity(city: CityResponse): Observable<CityResponse> {
    return this.httpService.post('/api/cities', '/', city);
  }
}
