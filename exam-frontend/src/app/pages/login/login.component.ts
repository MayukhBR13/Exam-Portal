import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Route, Router } from '@angular/router';
import { LoginService } from 'src/app/service/loginService/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user={
    username:'',
    password:''
  }

  constructor(private snack:MatSnackBar,
    private loginService:LoginService,
    private router:Router) { }

  ngOnInit(): void {
  }

  onSubmit(){
    if(this.user.username==null || this.user.username.trim()=='')
    {
      this.snack.open('User Name is required !!!','OK',{
        duration: 3000
      });
      return;
    }
    if(this.user.password==null || this.user.password.trim()=='')
    {
      this.snack.open('Password is required !!!','OK',{
        duration: 3000
      });
      return;
    }
    this.loginService.generateToken(this.user).subscribe(
      (data:any)=>{
        console.log('success');
        console.log(data);

        this.loginService.loginUser(data.token);
        this.loginService.getCurrentUser().subscribe(
          (userData)=>{
            this.loginService.setUser(userData);
            console.log(userData); 
            
            if(this.loginService.getUserRole()=='ADMIN'){
                //admin dashboard
                this.router.navigate(['/admin']);
                //window.location.href='/admin';
                this.loginService.loginStatusSubject.next(true);
            }else if(this.loginService.getUserRole()=='NORMAL'){
                this.router.navigate(['/user-dashboard']);
                //user dashboard
                //window.location.href='/user-dashboard';
                this.loginService.loginStatusSubject.next(true);
            }else{
              this.loginService.logoutUser();
            }
          }
        );

       
        
      },
      (error)=>{
        console.log('Error :');
        console.log(error);
        this.snack.open('Invalid Username Or Password!! Try again.','',{
          duration:3000
        })
      }
    );
  }

  

}
