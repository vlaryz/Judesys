import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { tap } from 'rxjs';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html'
})
export class RegisterComponent {

  public registrationFormGroup = new FormGroup({
    userName: new FormControl<string>({ value: '', disabled: false }, Validators.required),
    name: new FormControl<string>({ value: '', disabled: false }, Validators.required),
    surname: new FormControl<string>({ value: '', disabled: false }, Validators.required),
    email: new FormControl<string>({ value: '', disabled: false }, Validators.required),
    password: new FormControl<string>({ value: '', disabled: false }, Validators.required)
  });

  constructor(
    private readonly authenticationService: AuthenticationService,
    private readonly router: Router
  ) { }

  public register(): void {
    this.authenticationService.register(this.registrationFormGroup.value as any).pipe(
      tap(() => this.router.navigateByUrl('/login'))
    ).subscribe();
  }

}
