import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Post } from 'src/app/models/post';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { LikeService } from 'src/app/services/like.service';
import { PostService } from 'src/app/services/post.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private router: Router, private postService: PostService, private auth: AuthService, private likeService: LikeService) {}
  ngOnInit(): void {
    this.getAllPosts();
    console.log('ALL posts', this.allPosts);
    this.user = this.auth.getCurrentUser();
    console.log('here is my user =>' , this.user);
  }

  // currentUserId: number | undefined;
  user: any;
  post: Post | undefined;
  allPosts: Post[] = [];
  // isLiked: boolean = false;

  public getAllPosts() {
    this.postService.getAllPosts().subscribe(
      (response: Post[]) => {
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
        });
        this.allPosts = response;
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
    // const getUserId = localStorage.getItem('userId');
    // this.currentUserId = +getUserId!;
    this.postService.createUserPost(this.user.id, createPostForm.value).subscribe(
      (response: Post) => {
        this.post = response;
        console.log('New user post created:', this.post);
        this.allPosts.push(this.post);
        console.log('All posts:', this.allPosts);
        this.getAllPosts();
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
        // post.isLikedChk = !post.isLikedChk;
        console.log('is this liked? ', post.isLikedChk);
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
    this.router.navigate(['/login']);
  }
}
