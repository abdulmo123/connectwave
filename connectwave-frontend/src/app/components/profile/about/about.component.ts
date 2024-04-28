import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Friendship } from 'src/app/models/friendship';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { ProfileNavService } from 'src/app/services/profile-nav.service';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent implements OnInit {

  private userProfileId: number | undefined;
  userInfo: User | undefined;
  friendship: Friendship | undefined;
  currentUser: any;

  constructor(
    private profileNavService: ProfileNavService,
    private router: Router,
    private auth: AuthService
  ) {}

  ngOnInit(): void {
    this.currentUser = this.auth.getCurrentUser();
    this.userProfileId = this.profileNavService.getUserData();
    console.log('user profile id + ' , this.userProfileId);
    this.getUserProfileInfo();
  }

  getUserProfileInfo() {
    let tempid = JSON.parse(localStorage.getItem('userProfileId') || '');
    this.userProfileId = +tempid!;

    this.currentUser = JSON.parse(window.localStorage.getItem("currentUser") || '{}');
    console.log('current logged in user! => ', this.currentUser);

    this.profileNavService.getUserProfileInfo(this.userProfileId!).subscribe(
      (response: User) => {
        this.userInfo = response;
        console.log(' user profile info => ', this.userInfo);
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

  navToPostsTab() {
    this.router.navigate(['/user/posts', this.userProfileId]);
  }

  onAddFriend() {
    this.profileNavService.addFriend(this.currentUser!.id, this.userProfileId!).subscribe(
      (response: Friendship) => {
        this.friendship = response;
        console.log('friendship => ', this.friendship);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
    console.log('clicking add button!')
  }
}
