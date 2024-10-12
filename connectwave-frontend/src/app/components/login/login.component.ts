import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { EmailService } from 'src/app/services/email.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: User = {
    id: 0,
    email: '',
    password: '',
    firstName: '',
    lastName: '',
    gender: '',
    bio: '',
    createdDate: undefined,
    lastLoginDate: undefined
  };

  constructor(private router: Router, private auth: AuthService, private emailService: EmailService) {};

  ngOnInit(): void {}

  @ViewChild('resetPwdForm') resetPwdForm: NgForm | undefined;

  resetPwd: any = { email: '' };

  navToSignUp() {
    this.router.navigate(['/signup']);
  }

  onLogin() {
    this.auth.login(this.user).subscribe(
      (response: any) => {
        console.log('response => ', response);
        this.user = response;
        console.log('success!');
        console.log(this.user.email,":",this.user.password);
        this.auth.setCurrentUser(this.user);
        this.router.navigate(['/home'])
      },
      (error: any) => {
        console.log('error');
        console.log(this.user.email,":",this.user.password);
        alert(error.message);
      }
    )
  }

  sendForgotPwdEmail(resetPwdForm: NgForm) {
    if (this.resetPwdForm!.valid) {
      console.log('user email ==>', this.resetPwd.email)
      this.emailService.sendForgotPwdEmail(this.resetPwd.email).subscribe(
        () => {
        console.log('email sent!');
        },
        (error: HttpErrorResponse) => {
          console.error('email NOT sent!!!', error);
        }
      )
    }

    this.resetPwd.email = ''
    resetPwdForm.reset();
  }
}
