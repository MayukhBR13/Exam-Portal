import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import baseUrl from '../helper'

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  public loginStatusSubject= new Subject<boolean>();
  
  constructor(private http:HttpClient) { }

  public getCurrentUser(){
    return this.http.get(baseUrl+'/current-user');
  }

  public generateToken(loginData:any){
    return this.http.post(baseUrl+'/generate-token',loginData);
  }
  public loginUser(token:any){
    localStorage.setItem('token',token);
    return true;
  }
  public isLoggedIn(){
    let tokenStr=localStorage.getItem('token');
    if(tokenStr==null || tokenStr==undefined || tokenStr=='')return false;
    
    return true;
  }
  public logoutUser(){
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    return true;
  }
  public getToken(){
    return localStorage.getItem('token');
  }
  public setUser(user:any){
    localStorage.setItem('user',JSON.stringify(user));
  }
  public getUser(){
    let lu = localStorage.getItem('user');
    if(lu!=null)return JSON.parse(lu);
    else{
      this.logoutUser();
      return null;
    }
  }
  public getUserRole(){
    let user=this.getUser();
    if(user!=null)return user.authorities[0].authority;
    return null; 
  }
}
