import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { AuthorizationService } from './authorization.service';
import { HttpService } from './http.service';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(
    private httpService: HttpService,
    private authorizationService: AuthorizationService,
  ) { }

  public login(login: FormData): Observable<{ access_token: string }> {
    return this.httpService.post<{ access_token: string }>('', '/login', login).pipe(
      tap(response => {
        this.authorizationService.registerToken(response.access_token)
      })
    );
  }

  public register(credentials: { email: string, password: string }): Observable<any> {
    return this.httpService.post<{ token: string }>('/api/users', '', credentials);
  }
}
