import { User } from "./user";

export class Post {
  id: number | undefined;
  content: string | undefined;
  createdDate: Date | undefined;
  user: User | undefined;
  formattedDate?: string;
  publisherName?: string;
  isLikedChk?: boolean;
  isLiked?: string;
}
