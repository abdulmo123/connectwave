import { Post } from "./post";

export interface User {
  id: number,
  email: string,
  password: string,
  firstName : string,
  lastName: string,
  gender: string,
  bio: string,
  createdDate? : Date,
  lastLoginDate? : Date,
  userPosts?: Post[],
}
