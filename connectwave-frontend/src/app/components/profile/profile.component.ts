import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Friendship } from 'src/app/models/friendship';
import { Post } from 'src/app/models/post';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { LikeService } from 'src/app/services/like.service';
import { PostService } from 'src/app/services/post.service';
import { ProfileService } from 'src/app/services/profile.service';
import { UserService } from 'src/app/services/user.service';

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
  userFriendships: User[] = [];

  constructor(
    private profileService: ProfileService,
    private router: Router,
    private auth: AuthService,
    private postService: PostService,
    private likeService: LikeService,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.getUserProfileInfo();
    this.userProfileId = this.profileService.getUserData();
    console.log('user profile id + ' , this.userProfileId);
    this.currentUser = this.auth.getCurrentUser();
    console.log('current user logged in id + ', this.currentUser.id);
    this.getExistingFriendshipRequest();
    this.selectedTab = 'About';
  }

  getUserProfileId() {
    let tempid = JSON.parse(localStorage.getItem('userProfileId') || '');
    this.userProfileId = +tempid!;
  }


  handleLogout() {
    localStorage.removeItem('currentUser')
    localStorage.removeItem('likedPosts');
    this.router.navigate(['/login']);
  }

  getUserProfileInfo() {
    let tempid = JSON.parse(localStorage.getItem('userProfileId') || '');
    this.userProfileId = +tempid!;

    this.profileService.getUserProfileInfo(this.userProfileId!).subscribe(
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
  }

  navToCommentsTab() {
    this.selectedTab = 'Comments';
    console.log('selected comments tab ! =>', this.selectedTab);
  }

  navToAboutTab() {
    this.selectedTab = 'About';
    console.log('selected about tab ! =>', this.selectedTab);
  }

  navToFriendsTab() {
    this.selectedTab = 'Friends';
    console.log('selected friends tab ! =>', this.selectedTab);
    this.userService.getUserFriendships(this.userProfileId!).subscribe(
      (response: User[]) => {
        this.userFriendships = response;
        console.log('user friendships =>', this.userFriendships);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  onAddFriend() {
    let tempid = JSON.parse(localStorage.getItem('userProfileId') || '');
    this.userProfileId = +tempid!;

    this.profileService.addFriend(this.currentUser!.id, this.userProfileId!).subscribe(
      (response: Friendship) => {
        console.log('response response ', response)
        this.friendship = response;
        console.log('friendship => ', this.friendship);
        console.log('sender =>', this.friendship.sender);
        console.log('receiver =>', this.friendship.receiver);
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

    this.profileService.getExistingFriendshipRequest(this.currentUser!.id, this.userProfileId).subscribe(
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

  onCancelSentFriendshipRequest() {
    let tempid = JSON.parse(localStorage.getItem('userProfileId') || '');
    this.userProfileId = +tempid!;

    this.profileService.cancelSentFriendshipRequest(this.currentUser!.id, this.userProfileId).subscribe(
      (response: any) => {
        this.friendship = response
        console.log('cancel sent friendship request button clicked')
        console.log('friendship => ', this.friendship)
        this.getExistingFriendshipRequest();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )

  }
}
