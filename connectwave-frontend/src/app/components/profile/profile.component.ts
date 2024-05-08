import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Friendship } from 'src/app/models/friendship';
import { Post } from 'src/app/models/post';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { LikeService } from 'src/app/services/like.service';
import { PostService } from 'src/app/services/post.service';
import { ProfileNavService } from 'src/app/services/profile-nav.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  private userProfileId: number | undefined;
  userInfo: User | undefined;
  friendship: Friendship | undefined;
  currentUser: any;
  selectedTab: string | undefined;
  userPosts: Post[] = [];
  userLikes: Post[] = [];

  constructor(
    private profileNavService: ProfileNavService,
    private router: Router,
    private auth: AuthService,
    private postService: PostService,
    private likeService: LikeService
  ) {}

  ngOnInit(): void {
    this.getUserProfileInfo();
    this.userProfileId = this.profileNavService.getUserData();
    console.log('user profile id + ' , this.userProfileId);
    this.currentUser = this.auth.getCurrentUser();
    console.log('current user logged in id + ', this.currentUser.id);
    this.getExistingFriendshipRequest();
    this.selectedTab = 'About';
  }

  getUserProfileInfo() {
    let tempid = JSON.parse(localStorage.getItem('userProfileId') || '');
    this.userProfileId = +tempid!;

    // this.currentUser = JSON.parse(window.localStorage.getItem("currentUser") || '{}');
    // console.log('current logged in user! => ', this.currentUser);

    this.profileNavService.getUserProfileInfo(this.userProfileId!).subscribe(
      (response: User) => {
        this.userInfo = response;
        // this.profileNavService.setUserProfileInfo(this.userInfo!);
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

  // getAllUserPosts() {
  //   this.postService.getAllPostsByUser(this.userProfileId!).subscribe(
  //     (response: Post[]) => {
  //       this.userPosts = response;
  //       console.log('user posts =>', this.userPosts);
  //     },
  //     (error: HttpErrorResponse) => {
  //       alert(error.message);
  //     }
  //   )
  // }

  // getAllLikesByUser() {
  //   this.likeService.getAllLikesByUser(this.userProfileId!).subscribe(
  //     (response: Post[]) => {
  //       this.userLikes = response;
  //       console.log('user liked posts =>', this.userLikes);
  //     },
  //     (error: HttpErrorResponse) => {
  //       alert(error.message);
  //     }
  //   )
  // }

  navToPostsTab() {
    this.selectedTab = 'Posts';
    console.log('selected posts tab ! =>', this.selectedTab);
    this.postService.getAllPostsByUser(this.userProfileId!).subscribe(
      (response: Post[]) => {
        this.userPosts = response;
        console.log('user posts =>', this.userPosts);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
    // this.router.navigate(['/user/posts', this.userProfileId]);
  }

  navToLikesTab() {
    this.selectedTab = 'Likes';
    console.log('selected likes tab ! =>', this.selectedTab);
    this.likeService.getAllLikesByUser(this.userProfileId!).subscribe(
      (response: Post[]) => {
        this.userLikes = response;
        console.log('user liked posts =>', this.userLikes);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
    // this.router.navigate(['/user/posts', this.userProfileId]);
  }

  navToCommentsTab() {
    this.selectedTab = 'Comments';
    console.log('selected comments tab ! =>', this.selectedTab);
    // this.router.navigate(['/user/posts', this.userProfileId]);
  }

  navToAboutTab() {
    this.selectedTab = 'About';
    console.log('selected about tab ! =>', this.selectedTab);
    // this.router.navigate(['/user/posts', this.userProfileId]);
  }

  navToFriendsTab() {
    this.selectedTab = 'Friends';
    console.log('selected friends tab ! =>', this.selectedTab);
    // this.router.navigate(['/user/posts', this.userProfileId]);
  }

  onAddFriend() {
    let tempid = JSON.parse(localStorage.getItem('userProfileId') || '');
    this.userProfileId = +tempid!;

    this.profileNavService.addFriend(this.currentUser!.id, this.userProfileId!).subscribe(
      (response: Friendship) => {
        console.log('response response ', response)
        this.friendship = response;
        console.log('friendship => ', this.friendship);
        console.log('user =>', this.friendship.user);
        console.log('friend =>', this.friendship.friend);
        console.log('status =>', this.friendship.status);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
    console.log('clicking add button!')
  }

  getExistingFriendshipRequest() {
    let tempid = JSON.parse(localStorage.getItem('userProfileId') || '');
    this.userProfileId = +tempid!;

    this.profileNavService.getExistingFriendshipRequest(this.currentUser!.id, this.userProfileId).subscribe(
      (response: Friendship) => {
        this.friendship = response;
        console.log('friendship =>', this.friendship);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  onEditProfile() {
    console.log('edit profile button clicked!')
  }
}
