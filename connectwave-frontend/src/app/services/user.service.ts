import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment.development';
import { User } from '../models/user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
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

  public getUserFriendships(userId: number): Observable<User[]> {
    return this.http.get<User[]>(
      `${environment.hostUrl}/api/v1/users/${userId}/getUserFriendships`
    )
  }

  public getPendingSentFriendshipRequests(userId: number): Observable<User[]> {
    return this.http.get<User[]>(
      `${environment.hostUrl}/api/v1/users/${userId}/getPendingSentFriendshipRequests`
    )
  }

  public getPendingReceivedFriendshipRequests(userId: number): Observable<User[]> {
    return this.http.get<User[]>(
      `${environment.hostUrl}/api/v1/users/${userId}/getPendingReceivedFriendshipRequests`
    )
  }
}
