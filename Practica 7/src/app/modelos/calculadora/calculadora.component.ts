import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-calculadora',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './calculadora.component.html',
  styleUrls: ['./calculadora.component.css']
})
export class CalculadoraComponent {
  a: number = 0;
  b: number = 0;
  resultado: number | string = '';

  operar(op: string): void {
    switch (op) {
      case '+': this.resultado = this.a + this.b; break;
      case '-': this.resultado = this.a - this.b; break;
      case '*': this.resultado = this.a * this.b; break;
      case '/':
        this.resultado = this.b !== 0 ? this.a / this.b : 'Error: división por cero';
        break;
    }
  }
}
