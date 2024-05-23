import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.development';
import { User } from '../models/user';
import { Friendship } from '../models/friendship';

@Injectable({
  providedIn: 'root'
})
export class FriendshipService {

  constructor(private http: HttpClient) { }

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

  public responseToFriendshipRequest(senderId: number, receiverId: number, action: string): Observable<Friendship> {
    return this.http.post<Friendship>(
      `${environment.hostUrl}/api/v1/friendships/${senderId}/${receiverId}/${action}/respondToFriendshipRequest`,
      {})
  }

  public getExistingFriendshipRelationship(senderId: number, receiverId: number): Observable<Friendship> {
    return this.http.get<Friendship>(
      `${environment.hostUrl}/api/v1/friendships/${senderId}/${receiverId}/existingFriendshipRelationship`);
  }

  public removeExistingFriend(senderId: number, receiverId: number): Observable<void> {
    return this.http.delete<void>(
      `${environment.hostUrl}/api/v1/friendships/${senderId}/${receiverId}/removeExistingFriend`);
  }
}
