import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Post } from 'src/app/models/post';
import { Comment } from 'src/app/models/comment';
import { AuthService } from 'src/app/services/auth.service';
import { LikeService } from 'src/app/services/like.service';
import { PostService } from 'src/app/services/post.service';
import { CommentService } from 'src/app/services/comment.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private router: Router,
    private postService: PostService,
    private auth: AuthService,
    private likeService: LikeService,
    private commentService: CommentService) {}

  ngOnInit(): void {
    this.getAllPosts();
    this.user = this.auth.getCurrentUser();
    console.log('here is my user =>' , this.user);
    this.getAllLikesByUser();
    const likedPosts = JSON.parse(localStorage.getItem('likedPosts') || '[]');
    this.allPosts.forEach(post => {
      post.isLikedChk = likedPosts.includes(post.id);
    });
  }

  user: any;
  post: Post | undefined;
  comment: Comment | undefined;
  allPosts: Post[] = [];
  likedPosts: Post[] = [];

  public getAllPosts() {
    this.postService.getAllPosts().subscribe(
      (response: Post[]) => {
        this.allPosts = response;
        console.log('ALL posts', this.allPosts);
        response.forEach((post: Post) => {
          post.formattedDate = new Date(post.createdDate!).toLocaleString('en-US', {
            weekday: 'short',
            year: 'numeric',
            month: 'short',
            day: 'numeric',
            hour: 'numeric',
            minute: 'numeric',
            hour12: true
          });

          this.getNumLikesForPost(post);
          this.getNumCommentsForPost(post);

          if (post.postComments) {
            post.postComments.forEach((comment: Comment) => {
              comment.formattedDate = new Date(comment.createdDate!).toLocaleString('en-US', {
                weekday: 'short',
                year: 'numeric',
                month: 'short',
                day: 'numeric',
                hour: 'numeric',
                minute: 'numeric',
                hour12: true
              });
            });
          }
        });
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  dateFormatter() {
    const date = new Date();
    const formattedDateTime = date.toLocaleString('en-US', { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric', hour: 'numeric', minute: 'numeric', hour12: true });
    console.log(formattedDateTime);
  }

  public onCreateUserPost(createPostForm: NgForm) {
    console.log('Form data:', createPostForm.value);
    this.postService.createUserPost(this.user.id, createPostForm.value).subscribe(
      (response: Post) => {
        this.post = response;
        console.log('New user post created:', this.post);
        this.allPosts.push(this.post);
        console.log('All posts:', this.allPosts);
        this.getAllPosts();
        this.getAllLikesByUser();
        createPostForm.reset();
      },
      (error: HttpErrorResponse) => {
        console.error('Error creating post:', error);
        alert('An error occurred while creating the post. Please try again.');
      }
    );
  }

  toggleLike(post: Post) {

    post.isLikedChk = !post.isLikedChk;

    const isLiked = post.isLikedChk ? 'Y' : 'N';

    this.likeService.saveLikeStatus(this.user.id, post.id!, isLiked).subscribe(
      (response: any) => {
        console.log('like clicked!');
        console.log('userId', this.user.id);
        console.log('postId', post.id);
        post.isLiked = isLiked;
        console.log('is post liked?', post.isLiked);
        console.log('is this liked? ', post.isLikedChk);
        this.getNumLikesForPost(post);
      },

      (error: any) => {
        console.log('error');
        console.log('could not like/unlike the post!');
        alert(error.message);
        post.isLikedChk = !post.isLikedChk;
      }
    )

    console.log('is this liked? ', post.isLiked);
    console.log('post status =>', post);
    console.log('who am i? =>', this.user);
  }

  handleLogout() {
    localStorage.removeItem('currentUser')
    localStorage.removeItem('likedPosts');
    this.router.navigate(['/login']);
  }

  public getAllLikesByUser() {
    this.likeService.getAllLikesByUser(this.user.id!).subscribe(
      (response: Post[])  => {
        this.likedPosts = response;
        console.log('here are my liked posts =>', this.likedPosts)
        this.allPosts.forEach(post => {
          const likedPost = this.likedPosts.find(lp => lp.id === post.id)
          if (likedPost) {
            post.isLikedChk = true;
            post.isLiked = 'Y';
          }
        });

        localStorage.setItem('likedPosts', JSON.stringify(this.likedPosts));
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
      }
    )
  }

  public getNumLikesForPost(post: Post): void {
    this.likeService.getNumLikesForPost(post.id!).subscribe(
      (response: any) => {
        post.numOfLikes = response;
        console.log('num of likes for post => ', post.numOfLikes);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public getNumCommentsForPost(post: Post): void {
    this.commentService.getNumCommentsForPost(post.id!).subscribe(
      (response: any) => {
        post.numOfComments = response;
        console.log('num of comments for post => ', post.numOfComments);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  onAddCommentToPost(post: Post, createCommentForm: NgForm) {
    console.log('Form data:', createCommentForm.value);
    console.log('which post?', post);
    this.commentService.addCommentToPost(this.user.id, post.id!, createCommentForm.value).subscribe(
      (response: any) => {
        this.comment = response;
        console.log('new comment added to post!', this.comment);
        post?.postComments?.push(this.comment!);
        console.log('post info', post);
        this.getAllPosts();
        this.getAllLikesByUser();
        createCommentForm.reset();
      },
      (error: HttpErrorResponse) => {
        console.error('Error adding comment to post:', error);
        alert(error.message);
      }
    )
  }
}
