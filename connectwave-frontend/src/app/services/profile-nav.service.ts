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

  constructor(private http: HttpClient) { }

  getUserData() {
    return this.userId;
  }

  setUserData(userId: number) {
    this.userId = userId;
  }

  public getUserProfileInfo(userId: number): Observable<User> {
    return this.http.get<User>(`${environment.hostUrl}/api/v1/users/find/${userId}`);
  }

  public addFriend(userId: number, friendId: number): Observable<Friendship> {
    return this.http.post<Friendship>(
      `${environment.hostUrl}/api/v1/friendships/sendFriendshipRequest/${userId}/${friendId}`,
      {});
  }
}
