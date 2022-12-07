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

const routes: Routes = [
  { path: 'login', component: LoginComponent, canActivate: [UnauthorizedRouteGuard]},
  { path: 'register', component: RegisterComponent, canActivate: [UnauthorizedRouteGuard]},
  { path: '', component: AppContainerComponent, children: [
      { path: '', component: HomeScreenComponent},
      { path: 'cities', component: CitiesListComponent, canActivate: [AuthorizedRouteGuard]}
    ]
  }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ]
})
export class AppRoutingModule { }
