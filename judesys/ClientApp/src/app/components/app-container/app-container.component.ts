import { Component } from '@angular/core';
import {AuthenticationService} from "../../services/authentication.service";
import {AuthorizationService} from "../../services/authorization.service";
import {LogoutService} from "../../services/logout.service";
import { MatIconRegistry } from "@angular/material/icon";
import {DomSanitizer} from "@angular/platform-browser";
import jwt_decode from "jwt-decode";
import {JwtToken} from "../../models/jwtToken";

@Component({
  selector: 'app-app-container',
  templateUrl: './app-container.component.html',
  styleUrls: ['./app-container.component.scss']
})
export class AppContainerComponent {

  // public isLoggedIn: boolean = false;
  public userName: string = "";

  constructor(
    private authService: AuthorizationService,
    private logoutService: LogoutService,
    private mdIconRegistry: MatIconRegistry,
    private sanitizer: DomSanitizer
  ) {
    this.mdIconRegistry.addSvgIcon(
      `judesys`,
      this.sanitizer.bypassSecurityTrustResourceUrl("../assets/judesys.svg")
    );

    try {
      this.userName = (jwt_decode(authService.jwtToken) as JwtToken).sub;

    }
    catch (e) {
      console.log(e);
    }
    // console.log(authService.checkIfAdmin());
    // console.log(dec.sub);
    // this.isLoggedIn = authService.isAuthorized;
  }

  public checkIfLoggedIn(): boolean {
    return this.authService.isAuthorized;
  }

  public checkIfAdmin(): boolean {
    return true;
  }

  public logout(): void {
    this.logoutService.logout();
  }
}
