import { User } from "./user";

export class Blog {
  id: number | undefined;
  content: string | undefined;
  createdDate: Date | undefined;
  user: User | undefined;
  formattedDate?: string;
  publisherName?: string;
}
