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
    // const reqBody = {
    //   userId: userId,
    //   postId: postId,
    //   isLiked: isLiked
    // };

    return this.http.post<Post>(`${environment.hostUrl}/api/v1/likes/saveLikeStatus/${userId}/${postId}/${isLiked}`, {});
  }
}
