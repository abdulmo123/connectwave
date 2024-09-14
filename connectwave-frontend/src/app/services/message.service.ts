import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment.development';
import { Message } from '../models/message';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  constructor(private http: HttpClient) { }

  public getConversationMessages(senderId: number, receiverId: number): Observable<Message[]> {
    return this.http.get<Message[]>(`${environment.hostUrl}/api/v1/messages/${senderId}/${receiverId}/getConversationMessages`);
  }
}
