import { Component } from '@angular/core';
import {AuthenticationService} from "../../services/authentication.service";
import {AuthorizationService} from "../../services/authorization.service";
import {LogoutService} from "../../services/logout.service";
import { MatIconRegistry } from "@angular/material/icon";
import {DomSanitizer} from "@angular/platform-browser";

@Component({
  selector: 'app-app-container',
  templateUrl: './app-container.component.html',
  styleUrls: ['./app-container.component.scss']
})
export class AppContainerComponent {

  // public isLoggedIn: boolean = false;
  public isLogged = true;

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
    // this.isLoggedIn = authService.isAuthorized;
  }

  public checkIfLoggedIn(): boolean {
    // console.log("authorization: " + this.authService.isAuthorized);
    // console.log(this.authService.jwtToken);
    // console.log()
    return this.authService.isAuthorized;
  }

  public logout(): void {
    this.logoutService.logout();
  }
}
