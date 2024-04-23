import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { ProfileNavService } from 'src/app/services/profile-nav.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  private userId: number | undefined;
  userInfo: User | undefined;

  constructor(
    private profileNavService: ProfileNavService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.userId = this.profileNavService.getUserData();
    console.log('user id + ' , this.userId);
    this.getUserProfileInfo();
  }

  getUserProfileInfo() {
    let tempid = JSON.parse(localStorage.getItem('userProfileId') || '');
    this.userId = +tempid!;
    this.profileNavService.getUserProfileInfo(this.userId!).subscribe(
      (response: User) => {
        this.userInfo = response;
        console.log(' user info => ', this.userInfo);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  navToHome() {
    this.router.navigate(['/home']);
    localStorage.removeItem('userProfileId');
  }
}
