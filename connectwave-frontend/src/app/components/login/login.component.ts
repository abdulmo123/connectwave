import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

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
    bio: '',
    createdDate: undefined,
    lastLoginDate: undefined,
    userBlogPosts: undefined,
  };

  constructor(private router: Router, private auth: AuthService) {};
  ngOnInit(): void {}

  navToSignUp() {
    this.router.navigate(['/signup']);
  }

  onLogin() {
    this.auth.login(this.user).subscribe(
      (response: any) => {
        console.log('response => ', response);
        this.user = response;
        console.log('success!');
        console.log('my user ==>', this.user);
        console.log('user id =>', this.user.id);
        sessionStorage.setItem('userId', JSON.stringify(this.user.id));
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
}
