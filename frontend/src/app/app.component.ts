import { Component } from '@angular/core';
import axios from 'axios';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'frontend';

  constructor() {}

  async login(username: string = 'officer_101', password: string = 'password', source: string = 'postman') {
    const payload = {
      username, password, source
    };
    const response = await axios.post<{authorization: string}>('oru-auth/login', payload);
    if (response.status === 200) {
      localStorage.setItem('token', response.data.authorization);
    }
  }
}
