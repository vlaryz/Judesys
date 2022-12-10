import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AuthorizationService} from "./services/authorization.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {
  public greeting: string = "";

  public constructor(private readonly http: HttpClient, private authorizationService: AuthorizationService) {
  }

  public async ngOnInit(): Promise<void> {
    this.authorizationService.intializeAuthorization();

    // Check if development or prod deployment
    // let restUrl = location.origin + '/api/hello?name=Azure';
    // if (this.isDevelopment()) {

  }

  private isDevelopment = (): boolean => location.host.includes('localhost');
}
