import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CustomerDataBean } from './dataBean/CustomerDataBean';
import { HttpClient } from '@angular/common/http';



@Injectable()
export class CustomerService {


  constructor(private http: HttpClient) { }


  URL: string = "http://localhost:8080/api/customers";


  getCustomers()  {
    return this.http.get<CustomerDataBean[]>(this.URL);
  }

  createCustomer(customerData: CustomerDataBean) {
    return this.http.post(this.URL, customerData);
  }

  updateCustomer(customerId: number, updatedData: CustomerDataBean) {
    return this.http.put(this.URL + "/" +customerId, updatedData);
  }

  deleteCustomer(customerId: number) {
    return this.http.delete(this.URL + '/' + customerId);
  }



}
