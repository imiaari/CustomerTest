import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { CustomerDataBean } from './dataBean/CustomerDataBean';
import { CustomerService } from './customer.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  title = 'cutomerTest';
  customerForm: FormGroup;

  addEditCustomerAtt: CustomerDataBean = new CustomerDataBean;
  customers: CustomerDataBean[] = [];

  isAdd: boolean = true;

  constructor(private customerService: CustomerService) {

    this.customerForm = new FormGroup({
      // Define your form controls here
      'id': new FormControl(this.addEditCustomerAtt != null ? this.addEditCustomerAtt.id : '', null),
      'name': new FormControl(this.addEditCustomerAtt != null ? this.addEditCustomerAtt.name : '', null),
      'address': new FormControl(this.addEditCustomerAtt != null ? this.addEditCustomerAtt.address : '', null),
      'mobileNumber': new FormControl(this.addEditCustomerAtt != null ? this.addEditCustomerAtt.mobileNumber : '', null),
    });

  }



  ngOnInit(): void {

    this.getAllCustomer();

  }




  addEditCustomer() {
    const x = this.customerForm.value as CustomerDataBean;


    if (this.isAdd) {


      this.customerService.createCustomer(x).subscribe(
        (_result) => {

          alert("Customer added successfully.")
          this.customerForm.reset();
        },
        (error: any) => {
          console.log(error.error.message)

        }
      );
    } else {


      this.customerService.updateCustomer(this.customerForm.value.id, x).subscribe(
        (_result) => {

          alert("Customer updated successfully.");
          this.customerForm.reset();

        },
        (error: any) => {
          console.log(error.error.message)

        }
      );


    }


    setTimeout(() => {
      this.getAllCustomer();
    }, 1000);

  }


  updateCustomer(customer: CustomerDataBean) {

    console.log(customer);
    this.addEditCustomerAtt = customer;
    this.customerForm.setValue({
      id: customer.id,
      name: customer.name,
      address: customer.address,
      mobileNumber: customer.mobileNumber
    });
    this.isAdd = false;




  }





  getAllCustomer() {

    this.customerService.getCustomers().subscribe(
      (customers: CustomerDataBean[]) => {


        this.customers = customers;

      },
      (error: any) => {
        console.log(error);

      }
    );
  }

  deleteCustomer(id: number | undefined) {


    if (id !== undefined) {
      this.customerService.deleteCustomer(id).subscribe(
        (_result) => {

          this.getAllCustomer();
          alert("Customer deleted successfully");

        },
        (error: any) => {
          console.log(error);

        }
      );
    } else {
      console.log('Invalid customer ID provided');
      // Handle the case when the customer ID is undefined
    }

  }


}








