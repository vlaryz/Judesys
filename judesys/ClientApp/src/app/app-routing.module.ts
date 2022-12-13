import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from "@angular/router";
import {HomeScreenComponent} from "./components/home-screen/home-screen.component";
import {AppContainerComponent} from "./components/app-container/app-container.component";
import {LoginComponent} from "./components/login/login.component";
import {CitiesListComponent} from "./components/cities-list/cities-list.component";
import {UnauthorizedRouteGuard} from "./unauthorized-route.guard";
import {AuthorizedRouteGuard} from "./authorized-route.guard";
import {RegisterComponent} from "./components/register/register.component";
import {CityEventsComponent} from "./components/city-events/city-events.component";
import {EventListComponent} from "./components/event-list/event-list.component";
import {EventTicketBuyComponent} from "./components/event-ticket-buy/event-ticket-buy.component";
import {AdminPanelComponent} from "./components/admin-panel/admin-panel.component";
import {AccountDetailsComponent} from "./components/account-details/account-details.component";
import {AuthorizationService} from "./services/authorization.service";

const routes: Routes = [
  { path: '', component: AppContainerComponent, children: [
      { path: 'login', component: LoginComponent},
      { path: 'register', component: RegisterComponent},
      { path: 'admin', component: AdminPanelComponent, canActivate: [AuthorizedRouteGuard]},
      { path: 'account', component: AccountDetailsComponent},
      { path: '', component: HomeScreenComponent},
      { path: 'cities',component: CitiesListComponent},
      { path: 'cities/:id',component: CityEventsComponent},
      { path: 'cities/:id1/events/:id2', component: EventTicketBuyComponent}

    ]
  },
  { path: '**', component: HomeScreenComponent }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ]
})
export class AppRoutingModule { }
