import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import jwt_decode from "jwt-decode";
import {JwtToken} from "../models/jwtToken";

@Injectable({
  providedIn: 'root'
})
export class AuthorizationService {

  private readonly JwtTokenKey = 'JWT_TOKEN';

  private readonly jwtTokenSubject = new BehaviorSubject<string>('');

  public intializeAuthorization(): void {
    const existingToken = localStorage.getItem(this.JwtTokenKey);

    if (existingToken) {
      this.registerToken(existingToken);
    }
  }

  public registerToken(token: string): void {
    console.log("from request: " + token);
    localStorage.setItem(this.JwtTokenKey, token);

    this.jwtTokenSubject.next(token);
  }

  public clearToken(): void {
    localStorage.removeItem(this.JwtTokenKey);

    this.jwtTokenSubject.next('');
  }

  public get isAuthorized(): boolean {
    return !!this.jwtTokenSubject.value;
  }

  public get jwtToken(): string {
    return this.jwtTokenSubject.value;
  }

  public checkIfAdmin(): boolean {
    // console.log((jwt_decode(this.jwtToken) as JwtToken).roles);
    // return true;
    return (jwt_decode(this.jwtToken) as JwtToken).roles.includes("ROLE_ADMIN");
  }

  public getUserId(): string {
    const token = jwt_decode(this.jwtToken);
    const userId = (token as any).USER_ID;

    return userId;
  }
}
