import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment.development';
import { User } from '../models/user';


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  login(user: User) {
    const headers = new HttpHeaders({
      Authorization: 'Basic' +
      btoa(user.email + ':' + user.password)
    });

    return this.http.get(`${environment.hostUrl}/api/v1/login`, { headers, responseType: 'text' as 'json'});
  }

  signup(user: User) {
    return this.http.post(`${environment.hostUrl}/api/v1/signup`, user);
  }
}
