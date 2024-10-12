import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { HomeComponent } from './components/home/home.component';
import { ProfileComponent } from './components/profile/profile.component';
import { ResetPwdComponent } from './components/reset-pwd/reset-pwd.component';

const routes: Routes = [
  {path:"", redirectTo: "login", pathMatch: "full"},
  {path: "login", component: LoginComponent},
  {path: "signup", component: SignupComponent},
  {path: "home", component: HomeComponent},
  {path: "profiles/user/:userId", component: ProfileComponent},
  { path: "reset-password", component: ResetPwdComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
