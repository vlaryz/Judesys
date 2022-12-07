import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {
  public greeting: string = "";

  public constructor(private readonly http: HttpClient) {
  }

  public async ngOnInit(): Promise<void> {
    // Check if development or prod deployment
    // let restUrl = location.origin + '/api/hello?name=Azure';
    // if (this.isDevelopment()) {
    let restUrl = 'http://localhost:8080/api/cities/';
    // }
    await this.http.get(restUrl).subscribe(x => {this.greeting = String(x); console.log(x)});
  }

  private isDevelopment = (): boolean => location.host.includes('localhost');
}
