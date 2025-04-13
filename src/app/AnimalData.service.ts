import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { Animal } from './Animal';

@Injectable({
  providedIn: 'root'
})
export class AnimalDataService {
  private apiUrl: string = 'http://localhost:8080/api';
  private static imageFolder: string = '/images/pets/';

  constructor(private http: HttpClient) { }

  private static jsonToAnimal(rawAnimal: any): Animal {
    const animal: Animal = new Animal();

    animal.id = rawAnimal.id ?? 0;
    animal.name = rawAnimal.name ?? '';
    animal.type = rawAnimal.petKind ?? '';
    animal.age = rawAnimal.age ?? 0;
    animal.ownerId = rawAnimal.ownerId ?? 0;
    animal.image = rawAnimal.image ? AnimalDataService.imageFolder + rawAnimal.image : '';

    return animal;
  }

  public getPetsByOwnerId(ownerId: number): Observable<Animal[]> {
    const url = `${this.apiUrl}/owners/${ownerId}/pets`;

    console.log(`Fetching animals from URL: ${url}`);

    return this.http.get<any>(url).pipe(
      map(response => {
        const rawAnimals = response._embedded?.pets || [];
        return rawAnimals.map(AnimalDataService.jsonToAnimal);
      }),
      catchError(error => {
        console.error(`Error fetching animals for owner ${ownerId}:`, error);
        return of([]);
      })
    );
  }
}
