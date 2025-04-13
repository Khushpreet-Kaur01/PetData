import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Pet} from './pet';
import {catchError, map, Observable, of} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PetDataService {
  private apiUrl:string = 'http://localhost:8080/api';
  private static imageFolder: string = 'images/pets/';
  constructor(private http: HttpClient) { }
  private static jsonToPet(rawPet: any): Pet {
    const pet: Pet = new Pet();

    pet.id = rawPet.id ?? 0;
    pet.name = rawPet.name ?? '';
    pet.petKind = rawPet.petKind ?? ''; // Use the correct field name from the API
    pet.age = rawPet.age ?? 0;
    pet.ownerId = rawPet.ownerId ?? 0;

    pet.image = rawPet.image ? PetDataService.imageFolder + rawPet.image : ''; // add image folder
    return pet;
  }
  public getPetsByOwnerId(ownerId: number): Observable<Pet[]> {
    // --- Construct the URL dynamically ---
    // Use template literals (backticks `) to easily embed the ownerId
    const url = `${this.apiUrl}/owners/${ownerId}/pets`;
    // Example: if ownerId is 1, url becomes 'http://localhost:8080/api/owners/1/pets'

    console.log(`Workspaceing pets from URL: ${url}`); // Good for debugging

    // Make the GET request to the constructed URL
    return this.http.get<any>(url).pipe( // Use 'any' for HATEOAS response structure
      map(response => {
        // Extract pet array from _embedded, default to empty array if missing
        const rawPets = response._embedded?.pets || [];
        // Map using static helper
        return rawPets.map(PetDataService.jsonToPet);
      }),
      catchError(error => {
        console.error(`Error fetching pets for owner ${ownerId}:`, error);
        return of([]); // Return empty array on error
      })
    );
  }
}
