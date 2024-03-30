import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(private http: HttpClient) { }

  public addCommentToPost(userId: number, postId: number, userComment: Comment): Observable<Comment> {
    return this.http.post<Comment>(`${environment.hostUrl}/api/v1/comments/addCommentToPost/${userId}/${postId}`, userComment);
  }
}
