import { User } from "./user";
import { Comment } from "./comment";

export class Post {
  id: number | undefined;
  content: string | undefined;
  createdDate: Date | undefined;
  user: User | undefined;
  formattedDate?: string;
  publisherName?: string;
  isLikedChk?: boolean;
  isLiked?: string;
  postComments: Comment[] | undefined;
}
