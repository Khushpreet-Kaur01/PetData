import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { Owner } from './Owner';

@Injectable({
  providedIn: 'root'
})
export class OwnerDataService {
  private apiUrl: string = 'http://localhost:8080/api/owners';
  constructor(private http: HttpClient) { }

  private static jsonToOwner(rawOwner: any): Owner {
    const owner: Owner = new Owner();
    owner.id = rawOwner.id,
      owner.firstName = rawOwner.firstName,
      owner.lastName = rawOwner.lastName,
      owner.animalCount = rawOwner.petCount,
      owner.animalLink = rawOwner._links?.pets?.href
    return owner;
  }

  public getPetOwners(): Observable<Owner[]> {
    const url = `${this.apiUrl}`;
    return this.http.get<any>(url).pipe(
      map(response => {
        const rawOwners = response._embedded?.owners || [];
        return rawOwners.map(OwnerDataService.jsonToOwner);
      }),
      catchError(error => {
        console.error('Error fetching owners:', error);
        return of([]);
      })
    );
  }

  public getPetOwnerById(ownerId: number): Observable<Owner | undefined> {
    const url = `${this.apiUrl}/${ownerId}`;
    return this.http.get<any>(url).pipe(
      map(response => OwnerDataService.jsonToOwner(response)),
      catchError(error => {
        console.error(`Error fetching owner with id ${ownerId}:`, error);
        return of(undefined);
      })
    );
  }
}
