import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { tap } from 'rxjs';
// import { AuthenticationService } from 'src/app/services/authentication.service';
import {AuthenticationService} from "../../services/authentication.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent {


  public loginFormGroup = new FormGroup({
    email: new FormControl<string>({ value: '', disabled: false }, Validators.required),
    password: new FormControl<string>({ value: '', disabled: false }, Validators.required)
  });

  constructor(
    private readonly loginService: AuthenticationService,
    private readonly router: Router
  ) { }

  public login(): void {
    const formData: any = new FormData();
    formData.append('username', this.loginFormGroup.get('email')?.value);
    formData.append('password', this.loginFormGroup.get('password')?.value);

    console.log("formadata: " + formData.get('username'));
    console.log("formadata: " + formData.get('password'));
    this.loginService.login(formData).pipe(
      tap(() => this.router.navigateByUrl(''))
    ).subscribe();
  }
}
