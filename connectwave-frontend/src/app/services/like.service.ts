import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.development';
import { Post } from '../models/post';

@Injectable({
  providedIn: 'root'
})
export class LikeService {

  constructor(private http: HttpClient) { }

  public saveLikeStatus(userId: number, postId: number, isLiked: string): Observable<Post> {
    return this.http.post<Post>(`${environment.hostUrl}/api/v1/likes/${userId}/${postId}/${isLiked}/saveLikeStatus`, {});
  }

  public getAllLikesByUser(userId: number): Observable<Post[]> {
    return this.http.get<Post[]>(`${environment.hostUrl}/api/v1/likes/${userId}/getAllLikesByUser`);
  }
}
