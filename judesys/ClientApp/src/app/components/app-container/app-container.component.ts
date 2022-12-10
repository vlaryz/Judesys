import { Component } from '@angular/core';
import {AuthenticationService} from "../../services/authentication.service";
import {AuthorizationService} from "../../services/authorization.service";

@Component({
  selector: 'app-app-container',
  templateUrl: './app-container.component.html',
  styleUrls: ['./app-container.component.scss']
})
export class AppContainerComponent {

  // public isLoggedIn: boolean = false;
  constructor(
    private authService: AuthorizationService
  ) {
    // this.isLoggedIn = authService.isAuthorized;
  }

  public checkIfLoggedIn(): boolean {
    // console.log("authorization: " + this.authService.isAuthorized);
    // console.log(this.authService.jwtToken);
    return this.authService.isAuthorized;
  }
}
