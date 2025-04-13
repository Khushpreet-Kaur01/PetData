import { Component, OnInit } from '@angular/core';
import { OwnerDataService } from '../OwnerData.service';
import { Owner } from '../Owner';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import {
  MatTableModule,
  MatCell,
  MatCellDef,
  MatColumnDef,
  MatHeaderCell,
  MatHeaderCellDef,
  MatHeaderRow,
  MatHeaderRowDef,
  MatRow,
  MatRowDef,
} from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatChipsModule } from '@angular/material/chips';

@Component({
  selector: 'app-owners',
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
    MatHeaderCell,
    MatCell,
    MatHeaderRow,
    MatRow,
    MatRowDef,
    MatHeaderRowDef,
    MatCellDef,
    MatColumnDef,
    MatHeaderCellDef,
    MatButtonModule,
    MatIconModule,
    MatChipsModule,
  ],
  templateUrl: './owners.component.html',
})
export class PetOwnerListComponent implements OnInit {
  displayedColumns: string[] = ['id', 'firstName', 'lastName', 'petCount', 'actions'];
  petOwners: Owner[] = [];

  constructor(
    private ownerService: OwnerDataService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.ownerService.getPetOwners().subscribe((data) => {
      this.petOwners = data;
    });
  }

  viewPets(ownerId: number): void {
    this.router.navigate(['/pets', ownerId]);
  }
}
