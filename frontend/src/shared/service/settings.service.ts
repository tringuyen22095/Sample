import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { IUrlSettings } from '../model/url-settings.interface';

@Injectable({
    providedIn: 'root'
})
export class SettingsService {

    public url: IUrlSettings;
    public headers: any = {
        'Content-Type': 'application/json',
        'Cache-Control': 'public, max-age=600'
    }

    constructor() {
        this.url = {
            auth: `${environment.baseUrl}/auth`
        };
    }

}