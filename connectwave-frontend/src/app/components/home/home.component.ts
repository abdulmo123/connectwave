import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Blog } from 'src/app/models/blog';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { BlogService } from 'src/app/services/blog.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private blogService: BlogService, private auth: AuthService) {}
  ngOnInit(): void {
    this.getAllBlogPosts();
    console.log('ALL blog posts', this.allBlogPosts);
    this.user = this.auth.getCurrentUser();
    console.log('here is my user =>' , this.user);
  }

  currentUserId: number | undefined;
  user: any;
  blogPost: Blog | undefined;
  allBlogPosts: Blog[] = [];

  public getAllBlogPosts() {
    this.blogService.getAllBlogPosts().subscribe(
      (response: Blog[]) => {
        response.forEach((blogPost: Blog) => {
          blogPost.formattedDate = new Date(blogPost.createdDate!).toLocaleString('en-US', {
            weekday: 'short',
            year: 'numeric',
            month: 'short',
            day: 'numeric',
            hour: 'numeric',
            minute: 'numeric',
            hour12: true
          });
        });
        this.allBlogPosts = response;
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

  public onCreateUserBlogPost(createBlogPostForm: NgForm) {
    console.log('Form data:', createBlogPostForm.value);
    const getUserId = sessionStorage.getItem('userId');
    this.currentUserId = +getUserId!;
    this.blogService.createUserBlogPost(this.currentUserId!, createBlogPostForm.value).subscribe(
      (response: Blog) => {
        this.blogPost = response;
        console.log('New user blog post created:', this.blogPost);
        this.allBlogPosts.push(this.blogPost);
        console.log('All blog posts:', this.allBlogPosts);
        this.getAllBlogPosts();
        createBlogPostForm.reset();
      },
      (error: HttpErrorResponse) => {
        console.error('Error creating blog post:', error);
        alert('An error occurred while creating the blog post. Please try again.');
      }
    );
  }



}
