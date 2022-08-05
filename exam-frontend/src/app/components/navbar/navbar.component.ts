import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/service/loginService/login.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  username='';
  constructor(public login:LoginService) { }

  ngOnInit(): void {
    let u=this.login.getUser();
    if(u!=null)
      this.username=this.login.getUser().username;
    this.login.loginStatusSubject.asObservable().subscribe((data)=>{
      this.username=this.login.getUser().username;
    });
  }

  
  public logout(){
    if(this.login.logoutUser())
      this.username=''; 
    window.location.reload();
  }
}
