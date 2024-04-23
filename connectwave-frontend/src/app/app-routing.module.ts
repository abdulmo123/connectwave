import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { HomeComponent } from './components/home/home.component';
import { AboutComponent } from './components/profile/about/about.component';
import { PostsComponent } from './components/profile/posts/posts.component';

const routes: Routes = [
  {path:"", redirectTo: "login", pathMatch: "full"},
  {path: "login", component: LoginComponent},
  {path: "signup", component: SignupComponent},
  {path: "home", component: HomeComponent},
  {path: "user/about/:userId", component: AboutComponent},
  {path: "user/posts/:userId", component: PostsComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
