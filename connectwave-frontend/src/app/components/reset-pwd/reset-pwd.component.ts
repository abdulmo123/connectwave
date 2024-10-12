import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-reset-pwd',
  templateUrl: './reset-pwd.component.html',
  styleUrls: ['./reset-pwd.component.css']
})
export class ResetPwdComponent implements OnInit {

  newPassword: string = '';
  token: string | null = null;

  ngOnInit(): void {
    this.token = this.route.snapshot.queryParamMap.get("token");
    console.log('token ==>', this.token);
  }

  constructor (private route: ActivatedRoute, private router: Router, private userService: UserService) {}

  onResetPwd() {
    if (this.token) {
      this.userService.resetPassword(this.token, this.newPassword).subscribe(
        (response: any) => {
          console.log('Password successfully changed!');
          // You might want to redirect or show a success message here
          setTimeout(() => this.router.navigate(['/login']), 3000); // Redirect to login after successful reset
        },
        (error: HttpErrorResponse) => {
          console.error('Error', error);
          // Handle error here, e.g., show an error message to the user
        }
      );
    }
  }
}
