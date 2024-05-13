import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.development';
import { User } from '../models/user';
import { Friendship } from '../models/friendship';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  userId: number | undefined;
  userProfileInfo: User | undefined;

  constructor(private http: HttpClient) { }

  getUserData() {
    return this.userId;
  }

  setUserData(userId: number) {
    this.userId = userId;
  }

  setUserProfileInfo(userProfileInfo: User) {
    this.userProfileInfo = userProfileInfo;
  }

  public getUserProfileInfo(userId: number): Observable<User> {
    return this.http.get<User>(`${environment.hostUrl}/api/v1/users/find/${userId}`);
  }

  public addFriend(senderId: number, receiverId: number): Observable<Friendship> {
    return this.http.post<Friendship>(
      `${environment.hostUrl}/api/v1/friendships/${senderId}/${receiverId}/sendFriendshipRequest`,
      {});
  }

  public getExistingFriendshipRequest(senderId: number, receiverId: number): Observable<Friendship> {
    return this.http.get<Friendship>(
      `${environment.hostUrl}/api/v1/friendships/${senderId}/${receiverId}/existingFriendshipRequest`);
  }

  public cancelSentFriendshipRequest(senderId: number, receiverId: number): Observable<void> {
    return this.http.delete<void>(
      `${environment.hostUrl}/api/v1/friendships/${senderId}/${receiverId}/cancelSentFriendshipRequest`);
  }
}
