import { Routes } from '@angular/router';
import { PetOwnerListComponent } from './owners/owners.component';
import { AnimalListComponent } from './pets/pets.component';

export const routes: Routes = [
  { path: '', redirectTo: '/owners', pathMatch: 'full' },
  { path: 'owners', component: PetOwnerListComponent },
  { path: 'pets/:ownerId', component: AnimalListComponent }
];
