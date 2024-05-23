import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { FriendshipService } from 'src/app/services/friendship.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  user: any;
  pendingReceivedFriendshipRequest: User[] = [];

  constructor(private userService: UserService,
    private auth: AuthService,
    private router: Router,
    private friendshipService: FriendshipService) {}

  ngOnInit() {
    this.user = this.auth.getCurrentUser();
    this.getPendingReceivedFriendshipRequests();
  }

  getPendingReceivedFriendshipRequests() {
    this.userService.getPendingReceivedFriendshipRequests(this.user.id).subscribe(
      (response: User[]) => {
        this.pendingReceivedFriendshipRequest = response;
        console.log('user pending received friendship requests =>',
        this.pendingReceivedFriendshipRequest);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  onAccept(pendingRequest: User) {
    let action = 'Accept'
    console.log('here is the pending user id =>', pendingRequest.id)
    console.log('in accept method')
    this.friendshipService.responseToFriendshipRequest(pendingRequest.id, this.user.id, action).subscribe(
      (response) => {
        console.log('friendship request ACCEPTED! => ', response);
        this.getPendingReceivedFriendshipRequests();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  onReject(pendingRequest: User) {
    let action = 'Reject'
    console.log('here is the pending user id =>', pendingRequest.id)
    console.log('in reject method')
    this.friendshipService.responseToFriendshipRequest(pendingRequest.id, this.user.id, action).subscribe(
      (response) => {
        console.log('friendship request REJECTED! => ', response);
        this.getPendingReceivedFriendshipRequests();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  handleLogout() {
    localStorage.removeItem('currentUser')
    localStorage.removeItem('likedPosts');
    this.router.navigate(['/login']);
  }

  navToHome() {
    this.router.navigate(['/home']);
    localStorage.removeItem('userProfileId');
  }
}
