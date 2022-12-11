import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {EventsResponse} from "../../models/eventsResponse";
import {CityResponse} from "../../models/cityResponse";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {CitiesService} from "../../services/cities.service";

@Component({
  selector: 'app-city-edit',
  templateUrl: './city-edit.component.html',
  styleUrls: ['./city-edit.component.css']
})
export class CityEditComponent implements OnInit {

  public city: CityResponse = {} as CityResponse;

  public cityFormGroup = new FormGroup({
    name: new FormControl<string>(''),
    image: new FormControl<string>(''),
  });

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: { act: string, city: CityResponse },
    private citiesService: CitiesService
  ) { }

  ngOnInit(): void {
    this.city = this.data.city;
    console.log("tss" + this.city.name);

    this.cityFormGroup.setValue({
      name: this.city.name!,
      image: this.city.image!
    });
  }

  public saveCity(): void {
    const city = {
      name: this.cityFormGroup.get('name')!.value,
      image: this.cityFormGroup.get('image')!.value
    }
    if(this.data.act == "edit") {
      this.citiesService.editCity(this.city.id, city as CityResponse).subscribe(x => {
        console.log(x);
      })
    }
    else {
      this.citiesService.addCity(city as CityResponse).subscribe(x => {
        console.log(x);
      })
    }
  }

}
