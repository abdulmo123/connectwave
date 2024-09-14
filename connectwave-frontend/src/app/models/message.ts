import { User } from "./user";

export class Message {
  id: number | undefined;
  conversationId: number | undefined;
  sender: User | undefined;
  receiver: User | undefined;
  message: string | undefined;
  createdDate: Date | undefined;
}
