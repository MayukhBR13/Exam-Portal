import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from 'src/app/service/user.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  user={
    username:'',
    password:'',
    firstName:'',
    lastName:'',
    email:'',
    phone:''
  }

  constructor(private userService:UserService,private snack:MatSnackBar) { }

  ngOnInit(): void {
  }

  formSubmit(){
    if(this.user.username=='' || this.user.username==null){
      this.snack.open("User Name required!!",'OK',{
        duration:3000
      });
      return;
    }
    this.userService.addUser(this.user).subscribe(
      (data:any)=>{
        console.log(data);
        Swal.fire("Successfully done",'User id is '+data.id,"success");
      },
      (error)=>{
        console.log(error);
        this.snack.open('Something went wrong!','',{
          duration: 4000
        });
      }
    );
    
  }

}
