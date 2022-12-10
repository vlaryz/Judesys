import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { AuthorizationService } from './authorization.service';
import { HttpService } from './http.service';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(
    private httpService: HttpService,
    private authorizationService: AuthorizationService,
    private readonly httpClient: HttpClient
  ) { }

  public login(login: FormData): Observable<{ access_token: string }> {
    return this.httpService.post<{ access_token: string }>('', '/login', login).pipe(
      tap(response => {
        this.authorizationService.registerToken(response.access_token)
      })
    );
  }

  public register(credentials: { userName: string, name: string, surname: string, email: string, password: string }): Observable<any> {
    return this.httpService.post<{ token: string }>('/api/users', '/', credentials);
  }
}
