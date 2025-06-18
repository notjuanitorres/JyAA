import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { EventsComponent } from './modelos/events/events.component';
import { CalculadoraComponent } from './modelos/calculadora/calculadora.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, EventsComponent, CalculadoraComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Practica7';
}
