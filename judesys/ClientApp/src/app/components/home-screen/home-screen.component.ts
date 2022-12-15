import {Component, ElementRef, OnInit} from '@angular/core';
import {CitiesService} from "../../services/cities.service";
import * as SwaggerJson from "../../../../swagger.json";
import * as http from "http";
import {HttpService} from "../../services/http.service";

declare let Redoc: any

@Component({
  selector: 'app-home-screen',
  templateUrl: './home-screen.component.html',
  styleUrls: ['./home-screen.component.css']
})
export class HomeScreenComponent implements OnInit {

  constructor(
    private citiesService: CitiesService,
    private element: ElementRef,
    private httpService: HttpService
  ) { }

  ngOnInit(): void {
    // this.initDocs();
  }

  initDocs() {
    Redoc.init('http://localhost:8080/v2/api-docs?group=public-api', {
      scrollYOffset: 60,
      hideDownloadButton: true
    }, document.getElementById('redoc'))
  }
  //
  // ngAfterViewInit() {
  //   this.attachDocumentationComponent()
  // }
  //
  // async getSwaggerSpec(): Promise<any> {
  //   return this.httpService.get("http://localhost:8080/v2/api-docs?group=public-api", '');
  //
  //   // swagger spec retrieval logic
  //
  // }
  //
  // async attachDocumentationComponent() {
  //   // const swaggerSpec = await this.getSwaggerSpec()
  //   // const elem = this.element.nativeElement.querySelector('.documentation-root')
  //
  //   // Redoc.init('http://localhost:8080/v2/api-docs?group=public-api', {
  //   //   scrollYOffset: 50
  //   // }, document.getElementById('.documentation-root'), this.element)
  // }
  //
  // ngOnDestroy(): void {
  //   this.element.nativeElement.querySelector('.documentation-root').remove()
  // }

}
