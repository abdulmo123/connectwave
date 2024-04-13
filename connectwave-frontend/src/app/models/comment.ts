import { User } from "./user";
import { Post } from "./post";

export class Comment {
  id: number | undefined;
  content: string | undefined;
  createdDate: Date | undefined;
  user: User | undefined;
  post: Post | undefined;
  publisherName?: string;
  formattedDate?: string;
}
