import { User } from "./user";
import { Comment } from "./comment";

export class Post {
  id: number | undefined;
  content: string | undefined;
  createdDate: Date | undefined;
  user: User | null = null;
  formattedDate?: string;
  publisherName?: string;
  isLikedChk?: boolean;
  isLiked?: string;
  postComments: Comment[] | undefined;
  numOfLikes?: number;
  numOfComments?: number;
}
