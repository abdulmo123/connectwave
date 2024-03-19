import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Blog } from '../models/blog';
import { environment } from 'src/environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class BlogService {

  constructor(private http: HttpClient) { }

  public getAllBlogPosts(): Observable<Blog[]> {
    return this.http.get<Blog[]>(`${environment.hostUrl}/api/v1/blogs/getAllBlogPosts`);
  }

  public createUserBlogPost(userId: number, userBlogPost: Blog): Observable<Blog> {
    return this.http.post<Blog>(`${environment.hostUrl}/api/v1/blogs/createUserBlogPost/${userId}`, userBlogPost);
  }
}
