import { Directive, HostListener, ElementRef, OnInit } from '@angular/core';

@Directive({
  selector: '[textareaAutoresize]'
})
export class TextareaAutoresizeDirective implements OnInit { 

  constructor(private elementRef: ElementRef) { }

  @HostListener(':input')
  onInput() {
    this.elementRef.nativeElement.style.height = `80px`;
    this.resize();
  }

  @HostListener('ngModelChange', ['$event'])
  onModelChange(_event: any) {
    this.elementRef.nativeElement.style.height = `80px`;
    this.resize();
  }

  ngOnInit() {
    this.resize();
  }

  resize() {
    const height = this.elementRef.nativeElement.style.height;
    const scroolHeight: number = this.elementRef.nativeElement.scrollHeight;

    if (Number(height.substring(0, height.length - 2)) < scroolHeight) {
      this.elementRef.nativeElement.style.height = `${scroolHeight + 2}px`;
    }
  }
}