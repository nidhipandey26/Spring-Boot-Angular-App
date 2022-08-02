import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { Supervisor } from './supervisor';
import { SupervisorService } from './supervisor.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'supervisor-app';

  constructor(private supervisorService: SupervisorService) { }
  supervisor: Supervisor = new Supervisor();
  supervisors: Observable<Object[]> | undefined;
  SupervisorList!: [];
  submitted = false;
  isemailRequired = false;
  isphoneRequired = false;

  ngOnInit() {
    this.submitted = false;
    this.getSupervisors();
  //  console.log(this.isemailRequired + "Email ")
   // console.log(this.isphoneRequired + "Phone ")
    this.checkemail();
    this.checkphone();
  }

  supervisorsaveform = new FormGroup({
    firstName: new FormControl('', [Validators.required, Validators.minLength(5),Validators.pattern('^[a-zA-Z]+$')]),
    lastName: new FormControl('', [Validators.required, Validators.minLength(5)]),
    email: new FormControl('', [Validators.required, Validators.email]),
    phoneNo: new FormControl('', [Validators.required, Validators.maxLength(10)]),
    supervisorLst: new FormControl('', [Validators.required]),

  });

  saveSupervisor(saveSupervisor: any) {
    this.supervisor = new Supervisor();
    this.supervisor.firstName = this.FirstName?.value || '';
    this.supervisor.lastName = this.LastName?.value  || '';
    this.supervisor.phoneNo = this.phoneno?.value  || '';
    this.supervisor.email = this.email?.value  || '';
    this.supervisor.supervisor = this.supervisorlist?.value  || '';

    this.submitted = true;
    console.log(this.saveSupervisor)
    this.save();
  }

  save() {
    this.supervisorService.createSupervisor(this.supervisor)
      .subscribe(data => console.log(data), error => console.log(error));
    this.supervisor = new Supervisor();
    console.log(this.supervisor);
  }

  getSupervisors() {
    this.supervisorService.getSupervisor().subscribe(data =>
      this.SupervisorList = data)
      console.log(this.SupervisorList)
  }
  checkphone() {
    this.isphoneRequired = true;
    this.supervisorsaveform.get('phoneNo')?.enable();
    console.log(this.isphoneRequired)
  }
  checkemail() {
    this.isemailRequired ? false : true
    this.supervisorsaveform.get('email')?.enable();
    console.log(this.isemailRequired)

  }
  get FirstName() {
    return this.supervisorsaveform.get('firstName');
  }
  get LastName() {
    return this.supervisorsaveform.get('lastName');
  }
  get phoneno() {
    return this.supervisorsaveform.get('phoneNo');
  }
  get email() {
    return this.supervisorsaveform.get('email');
  }
  get supervisorlist() {
    return this.supervisorsaveform.get('supervisorLst');
  }

  addSupervisorForm() {
    this.submitted = false;
    this.supervisorsaveform.reset();
  }
}
