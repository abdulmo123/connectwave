import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Post } from 'src/app/models/post';
import { User } from 'src/app/models/user';
import { PostService } from 'src/app/services/post.service';
import { ProfileNavService } from 'src/app/services/profile-nav.service';

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.css']
})
export class PostsComponent implements OnInit {

  private userId: number | undefined;
  userInfo: User | undefined;
  userPosts: Post[] = [];

  constructor(
    private router: Router,
    private profileNavService: ProfileNavService,
    private postService: PostService) {}

  ngOnInit(): void {
    // this.userId = this.profileNavService.getUserData();
    this.getUserProfileInfo();
    this.getAllUserPosts();
  }


  navToAboutTab() {
    let tempid = JSON.parse(localStorage.getItem('userProfileId') || '');
    this.userId = +tempid!;
    this.router.navigate(['/user/about', this.userId]);
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

  getAllUserPosts() {
    this.postService.getAllPostsByUser(this.userId!).subscribe(
      (response: Post[]) => {
        this.userPosts = response;
        console.log('user posts =>', this.userPosts);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  navToHome() {
    this.router.navigate(['/home']);
  }

  navToLikes() {
    this.router.navigate(['/likes', this.userId]);
  }

  navToComments() {
    this.router.navigate(['/comments', this.userId]);
  }
}
