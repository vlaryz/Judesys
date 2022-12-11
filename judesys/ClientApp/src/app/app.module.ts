import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';

import { HttpClientModule } from '@angular/common/http';
import {RouterModule} from "@angular/router";
import { AppRoutingModule } from './app-routing.module';

import { HomeScreenComponent } from './components/home-screen/home-screen.component';
import {LoginComponent} from "./components/login/login.component";
import {AppContainerComponent} from "./components/app-container/app-container.component";
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatTooltipModule } from '@angular/material/tooltip';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatIconModule} from "@angular/material/icon";
import {MatButtonModule} from "@angular/material/button";
import {MatFormFieldModule} from "@angular/material/form-field";
import {ReactiveFormsModule} from "@angular/forms";
import {MatCardModule} from "@angular/material/card";
import {MatInputModule} from "@angular/material/input";
import { CitiesListComponent } from './components/cities-list/cities-list.component';
import {RegisterComponent} from "./components/register/register.component";
import {MatListModule} from "@angular/material/list";
import {FlexLayoutModule} from "@angular/flex-layout";
import {MatSidenavModule} from "@angular/material/sidenav";
import { CityEventsComponent } from './components/city-events/city-events.component';
import { EventDetailsComponent } from './components/event-details/event-details.component';
import { MatDialogModule } from '@angular/material/dialog';
import { EventTicketBuyComponent } from './components/event-ticket-buy/event-ticket-buy.component';
import { EventListComponent } from './components/event-list/event-list.component';
import { TicketPurchaseComponent } from './components/ticket-purchase/ticket-purchase.component';
import {MatSelectModule} from "@angular/material/select";

@NgModule({
  declarations: [
    AppComponent,
    HomeScreenComponent,
    AppContainerComponent,
    LoginComponent,
    RegisterComponent,
    CitiesListComponent,
    CityEventsComponent,
    EventDetailsComponent,
    EventTicketBuyComponent,
    EventListComponent,
    TicketPurchaseComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    RouterModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatTooltipModule,
    MatIconModule,
    MatButtonModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatListModule,
    FlexLayoutModule,
    MatSidenavModule,
    MatSidenavModule,
    MatDialogModule,
    MatSelectModule,
  ],
  providers: [

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
