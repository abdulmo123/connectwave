import { User } from "./user";

export class Friendship {
  id: number | undefined;
  user: User | undefined;
  friend: User | undefined;
  status: string | undefined;
}
