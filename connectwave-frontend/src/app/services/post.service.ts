import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Post } from '../models/post';
import { environment } from 'src/environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private http: HttpClient) { }

  public getAllPosts(): Observable<Post[]> {
    return this.http.get<Post[]>(`${environment.hostUrl}/api/v1/posts/getAllPosts`);
  }

  public createUserPost(userId: number, userPost: Post): Observable<Post> {
    return this.http.post<Post>(`${environment.hostUrl}/api/v1/posts/createUserPost/${userId}`, userPost);
  }
}
