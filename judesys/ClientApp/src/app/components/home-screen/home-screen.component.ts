import { Component, OnInit } from '@angular/core';
import {CitiesService} from "../../services/cities.service";

@Component({
  selector: 'app-home-screen',
  templateUrl: './home-screen.component.html',
  styleUrls: ['./home-screen.component.css']
})
export class HomeScreenComponent implements OnInit {

  constructor(private citiesService: CitiesService) { }

  ngOnInit(): void {
    this.citiesService.getCities().subscribe(x => {
      console.log(x);
    })
  }

}
