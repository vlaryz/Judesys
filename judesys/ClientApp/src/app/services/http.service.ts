import { Inject, Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpParams, HttpStatusCode } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  private baseUrl = "https://web-app-judesys.azurewebsites.net";
  // private baseUrl = "http://localhost:8080";

  constructor(
    private readonly httpClient: HttpClient
    ) { }

    public get<TResponse = any>(controller: string, endpoint: string): Observable<TResponse> {
      const request = this.httpClient.get<TResponse>(this.baseUrl + controller + endpoint);
      return request;
    }

    public delete<TResponse = any>(controller: string, endpoint: string): Observable<TResponse> {
      const request = this.httpClient.delete<TResponse>(this.baseUrl + controller + endpoint);
      return request;
    }

    public post<TResponse = any>(controller: string, endpoint: string, body: any): Observable<TResponse> {
      const request = this.httpClient.post<TResponse>(this.baseUrl + controller + endpoint, body);
      return request;
    }

    public put<TResponse = any>(controller: string, endpoint: string, body: any): Observable<TResponse> {
    const request = this.httpClient.put<TResponse>(this.baseUrl + controller + endpoint, body);
    return request;
  }

}
