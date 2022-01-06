import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Employee } from './employee';
import { EmployeeService } from './employee.service';

@Component({
  selector: 'app-employees',
  templateUrl: './employees.component.html',
  styleUrls: ['./employees.component.scss']
})
export class EmployeesComponent implements OnInit {

  form: FormGroup;
  employees: Employee[];

  constructor(private employeeService: EmployeeService,
              private fb: FormBuilder) {
  }

  ngOnInit() {
    this.employeeService.getEmployees().subscribe(empFromDb => {
      this.employees = empFromDb.data;
      console.log(this.employees);
    })
    this.initForm();
  }

  private initForm(): void {
    this.form = this.fb.group({ // TODO: Add validations
      id: ['',[Validators.required, Validators.pattern(/^[0-9]*$/)]],
      name: ['',[Validators.required, Validators.pattern(/^[a-zA-Z\s]*$/)]],
      email: ['',[Validators.required, Validators.email]],
      avatar: ['',Validators.required]
    });
  }

  addEmployee(): void {
    if (this.form.valid) {
      const newEmployee: Employee = {
        id: this.form.get('id').value,
        name: this.form.get('name').value,
        email: this.form.get('email').value,
        avatar: this.form.get('avatar').value
      };
      this.employees.push(newEmployee);
      this.initForm();
    }
  }

  deleteEmployee(employee): void {
    let index = this.employees.findIndex(emp => emp.id === employee.id);
    this.employees.splice(index,1);
  }
}
