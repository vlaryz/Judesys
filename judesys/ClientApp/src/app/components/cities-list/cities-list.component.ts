import { Component, OnInit } from '@angular/core';
import {CitiesService} from "../../services/cities.service";
import {CityResponse} from "../../models/cityResponse";
import {animate, state, style, transition, trigger} from "@angular/animations";

@Component({
  selector: 'app-cities-list',
  templateUrl: './cities-list.component.html',
  styleUrls: ['./cities-list.component.css'],
  animations: [
    trigger('fade', [
      transition('void => active', [ // using status here for transition
        style({ opacity: 0 }),
        animate(1000, style({ opacity: 1 }))
      ]),
      transition('* => void', [
        animate(1000, style({ opacity: 0 }))
      ])
    ])
  ]
})
export class CitiesListComponent implements OnInit {

  public cities: CityResponse [] = [];
  public state:string = 'active';

  constructor(
    private citiesService: CitiesService
  ) { }

  ngOnInit(): void {
    this.citiesService.getCities().subscribe((x: CityResponse[]) => {
      this.cities = x;
      console.log("cits: " + JSON.stringify(x));
    })
  }


  ngAfterViewInit() {
    setTimeout( () => {
      this.state = 'active';
    }, 200);
  }

}
