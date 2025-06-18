import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Evento } from '../evento';

@Component({
  selector: 'app-events',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.css']
})
export class EventsComponent {
  events: Evento[] = [
    new Evento('Lollapalooza con la GOAT', 'Artista revelación: Mon Laferte.', 'Estadio Municipal', '2023-10-15'),
    new Evento('Feria de Libros con la GOAT', 'Una feria con una gran variedad de libros y autores.', 'La Rural', '2023-11-05'),
    new Evento('Cine con la GOAT', 'Peliculón Lilo y Stitch.', 'Plaza Central', '2023-12-01')
  ];
}
