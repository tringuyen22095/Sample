import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import axios from 'axios';
import { ToastrModule, ToastrService } from 'ngx-toastr';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
  constructor(private toastService: ToastrService) {
    axios.interceptors.request.use(req => {
      req.baseURL = 'http://apstcb01dev.icc.crifnet.com:8080';
      req.headers['Content-Type'] = "application/json";
      req.headers.Accept = "application/json";
      if (localStorage.getItem('token'))
        req.headers.Authorization = localStorage.getItem('token');
      return req;
    });
    axios.interceptors.response.use(res => {
      return res;
    }, error => {
      this.toastService.error("Api call fail.", "Error!!");
      return error;
    });
  }
  
}
