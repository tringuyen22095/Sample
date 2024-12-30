import { Directive, HostListener, ElementRef, OnInit } from '@angular/core';

@Directive({
  selector: '[appTextareaAutoresize]'
})
export class TextareaAutoresizeDirective implements OnInit { 

  constructor(private elementRef: ElementRef) { }

  @HostListener(':input')
  onInput() {
    this.resize();
  }

  @HostListener('ngModelChange', ['$event'])
  onModelChange(event) {
    this.resize();
  }

  ngOnInit() {
  }

  resize() {
    const height = this.elementRef.nativeElement.style.height;
    const scroolHeight: number = this.elementRef.nativeElement.scrollHeight;

    if (Number(height.substring(0, height.length - 2)) < scroolHeight) {
      this.elementRef.nativeElement.style.height = `${scroolHeight + 2}px`;
    }
  }
}