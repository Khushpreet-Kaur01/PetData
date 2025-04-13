import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {PetOwner} from './pet-owner';
import {catchError, map, Observable, of} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PetOwnerDataService {
  private apiUrl:string = 'http://localhost:8080/api/owners';
  constructor(private http: HttpClient) { }
  private static jsonToPetOwner(rawOwner: any): PetOwner {
    const petOwner: PetOwner = new PetOwner();
    petOwner.id=rawOwner.id,
      petOwner.firstName= rawOwner.firstName,
      petOwner.lastName=rawOwner.lastName,
      petOwner.petCount= rawOwner.petCount,
      // Extract the specific 'pets' link from the HATEOAS structure
      petOwner.petLink= rawOwner._links?.pets?.href
    return petOwner;
  }
  public getPetOwners(): Observable<PetOwner[]> {
    const url = `${this.apiUrl}`; // Endpoint for all owners
    return this.http.get<any>(url).pipe( // Use 'any' to parse HATEOAS
      map(response => {
        // Extract owner array from _embedded, default to empty array if missing
        const rawOwners = response._embedded?.owners || [];
        // Map each raw owner using the static helper method
        return rawOwners.map(PetOwnerDataService.jsonToPetOwner);
      }),
      catchError(error => {
        console.error('Error fetching pet owners:', error);
        return of([]); // Return an empty array on error
      })
    );
  }
  public getPetOwnerById(ownerId: number): Observable<PetOwner | undefined> {
    const url = `${this.apiUrl}/${ownerId}`; // Endpoint for a single owner
    return this.http.get<any>(url).pipe( // Use 'any' for raw response
      map(response => PetOwnerDataService.jsonToPetOwner(response)), // Map using static helper
      catchError(error => {
        console.error(`Error fetching pet owner with id ${ownerId}:`, error);
        return of(undefined); // Return undefined on error
      })
    );
  }
}
