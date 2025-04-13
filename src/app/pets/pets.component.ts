import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router, RouterLinkWithHref} from '@angular/router';
import { AnimalDataService } from '../AnimalData.service';
import { OwnerDataService } from '../OwnerData.service';
import { Animal } from '../Animal';
import { Owner } from '../Owner';
import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import {MatButton} from '@angular/material/button';

@Component({
  selector: 'app-animals',
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
    MatCardModule,
    MatIconModule,
    MatButton,
    RouterLinkWithHref
      ],
  templateUrl: './pets.component.html',
  styleUrls: ['./pets.component.css']
})
export class AnimalListComponent implements OnInit {
  animals: Animal[] = [];
  currentOwner?: Owner;
  columnsToDisplay: string[] = ['image', 'name', 'type', 'age', 'ownerId'];

  constructor(
    private routerParams: ActivatedRoute,
    private animalDataProvider: AnimalDataService,
    private ownerDataProvider: OwnerDataService
  ) {}

  ngOnInit(): void {
    const ownerIdentifier = this.routerParams.snapshot.paramMap.get('ownerId');
    if (ownerIdentifier) {
      this.fetchOwnerDetails(+ownerIdentifier);
      this.fetchAnimals(+ownerIdentifier);
    }
  }

  private fetchOwnerDetails(ownerIdentifier: number): void {
    this.ownerDataProvider.getPetOwnerById(ownerIdentifier).subscribe(owner => {
      this.currentOwner = owner;
    });
  }

  private fetchAnimals(ownerIdentifier: number): void {
    this.animalDataProvider.getPetsByOwnerId(ownerIdentifier).subscribe(animals => {
      this.animals = animals;
    });
  }
}
