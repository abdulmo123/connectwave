import { User } from "./user";

export class Friendship {
  id: number | undefined;
  sender: User | undefined;
  receiver: User | undefined;
  status: string | undefined;
}
