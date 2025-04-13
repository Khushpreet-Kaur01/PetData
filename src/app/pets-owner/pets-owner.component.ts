import { Component } from '@angular/core';
import {Router} from '@angular/router';
import {PetOwnerDataService} from '../pet-owner-data.service';
import {PetOwner} from '../pet-owner';

@Component({
  selector: 'app-pets-owner',
  imports: [],
  templateUrl: './pets-owner.component.html',
  styleUrl: './pets-owner.component.css'
})
export class PetsOwnerComponent {
  petOwners: PetOwner[] = [];

  constructor(private ownerService: PetOwnerDataService, private router: Router) {}

  ngOnInit(): void {
    this.ownerService.getPetOwners().subscribe(data => this.petOwners = data);
  }

  viewPets(ownerId: number): void {
    this.router.navigate(['/pets', ownerId]);
  }

}
