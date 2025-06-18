export class Evento {
  name: string;
  description: string;
  place: string;
  date: string;

  constructor(name: string, description: string, place: string, date: string) {
    this.name = name;
    this.description = description;
    this.place = place;
    this.date = date;
  }
}
