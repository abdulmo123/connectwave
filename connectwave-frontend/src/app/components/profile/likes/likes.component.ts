import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Post } from 'src/app/models/post';
import { User } from 'src/app/models/user';
import { LikeService } from 'src/app/services/like.service';
import { ProfileNavService } from 'src/app/services/profile-nav.service';

@Component({
  selector: 'app-likes',
  templateUrl: './likes.component.html',
  styleUrls: ['./likes.component.css']
})
export class LikesComponent implements OnInit {

  private userId: number | undefined;
  userInfo: User | undefined;
  userLikes: Post[] = [];

  constructor(
    private router: Router,
    private profileNavService: ProfileNavService,
    private likeService: LikeService) {}

  ngOnInit(): void {
    this.getUserProfileInfo();
    this.getAllLikesByUser();
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

  getAllLikesByUser() {
    this.likeService.getAllLikesByUser(this.userId!).subscribe(
      (response: Post[]) => {
        this.userLikes = response;
        console.log('user liked posts =>', this.userLikes);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  navToHome() {
    this.router.navigate(['/home']);
  }

  navToCommentsTab() {
    this.router.navigate(['/user/comments', this.userId]);
  }

  navToAboutTab() {
    this.router.navigate(['/user/about', this.userId]);
  }

  navToPostsTab() {
    this.router.navigate(['/user/posts', this.userId]);
  }
}
