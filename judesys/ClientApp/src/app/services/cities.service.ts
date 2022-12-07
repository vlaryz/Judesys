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
}
