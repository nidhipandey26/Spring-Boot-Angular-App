import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';  

@Injectable({
  providedIn: 'root'
})
export class SupervisorService {

  constructor(private http:HttpClient) { }
  private baseUrl = 'http://localhost:8080/api';  
  
  createSupervisor(supervisor: object): Observable<object> {  
    return this.http.post(`${this.baseUrl}`+'/submit', supervisor);  
  }    
  
  getSupervisor(): Observable<any> {  
    return this.http.get(`${this.baseUrl}/supervisors/`);  
  }  
  
  
 
    
}
