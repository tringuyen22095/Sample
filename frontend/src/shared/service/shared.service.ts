import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class SharedService {
    private _isLoading: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
    private loadingCalledCount: number = 0;

    get isLoading(): Observable<boolean> {
        return this._isLoading.asObservable();
    }

    showLoading(): void {
        if (this.loadingCalledCount < 0)
            this.loadingCalledCount = 0;
        if (!this.loadingCalledCount)
            this._isLoading.next(true);
        this.loadingCalledCount++;
    }

    hideLoading(): void {
        this.loadingCalledCount--;
        if (!this.loadingCalledCount)
            this._isLoading.next(false);
    }
}