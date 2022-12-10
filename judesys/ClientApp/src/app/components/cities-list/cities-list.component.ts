import { Component, OnInit } from '@angular/core';
import {CitiesService} from "../../services/cities.service";
import {CityResponse} from "../../models/cityResponse";

@Component({
  selector: 'app-cities-list',
  templateUrl: './cities-list.component.html',
  styleUrls: ['./cities-list.component.css']
})
export class CitiesListComponent implements OnInit {

  public cities: CityResponse [] = [];
  constructor(
    private citiesService: CitiesService
  ) { }

  ngOnInit(): void {
    this.citiesService.getCities().subscribe((x: CityResponse[]) => {
      this.cities = x;
      console.log("cits: " + JSON.stringify(x));
    })
  }

}
