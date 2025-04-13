import { Routes } from '@angular/router';
import {PetsOwnerComponent} from './pets-owner/pets-owner.component';
import {PetsComponent} from './pets/pets.component';

export const routes: Routes = [
  { path: '', redirectTo: '/petsOwner', pathMatch: 'full' },
  { path: 'petsOwner', component: PetsOwnerComponent},
  { path: 'pets/:ownerId', component: PetsComponent }
];
