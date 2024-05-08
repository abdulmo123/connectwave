import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.development';
import { User } from '../models/user';
import { Friendship } from '../models/friendship';

@Injectable({
  providedIn: 'root'
})
export class ProfileNavService {

  userId: number | undefined;
  userProfileInfo: User | undefined;

  constructor(private http: HttpClient) { }

  getUserData() {
    return this.userId;
  }

  setUserData(userId: number) {
    this.userId = userId;
  }

  // getUserProfileInfo() {
  //   return this.userProfileInfo;
  // }

  setUserProfileInfo(userProfileInfo: User) {
    this.userProfileInfo = userProfileInfo;
  }

  public getUserProfileInfo(userId: number): Observable<User> {
    return this.http.get<User>(`${environment.hostUrl}/api/v1/users/find/${userId}`);
  }

  public addFriend(senderId: number, receiverId: number): Observable<Friendship> {
    return this.http.post<Friendship>(
      `${environment.hostUrl}/api/v1/friendships/sendFriendshipRequest/${senderId}/${receiverId}`,
      {});
  }

  public getExistingFriendshipRequest(senderId: number, receiverId: number): Observable<Friendship> {
    return this.http.get<Friendship>(
      `${environment.hostUrl}/api/v1/friendships/existingFriendshipRequest/${senderId}/${receiverId}`);
  }
}
