import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Client } from '../../../models/client.model';
import { ClientService } from '../../../services/client.service';

@Component({
  selector: 'app-client-form',
  templateUrl: './client-form.component.html',
  styleUrls: ['./client-form.component.css']
})
export class ClientFormComponent implements OnInit {
  clientForm!: FormGroup;
  isEditMode = false;
  clientId?: number;
  loading = false;
  error: string | null = null;
  submitted = false;

  constructor(
    private formBuilder: FormBuilder,
    private clientService: ClientService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.initForm();

    this.route.paramMap.subscribe(params => {
      const id = params.get('id');
      if (id) {
        this.isEditMode = true;
        this.clientId = +id;
        this.loadClient(this.clientId);
      }
    });
  }

  initForm(): void {
    this.clientForm = this.formBuilder.group({
      nom: ['', [Validators.required, Validators.maxLength(50)]],
      prenom: ['', [Validators.required, Validators.maxLength(50)]],
      dateNaissance: ['', Validators.required],
      telephone: ['', [Validators.required, Validators.pattern(/^[0-9+\-\s]+$/)]],
      email: ['', [Validators.required, Validators.email]]
    });
  }

  loadClient(id: number): void {
    this.loading = true;
    this.clientService.getClient(id).subscribe({
      next: (client) => {
        this.clientForm.patchValue({
          nom: client.nom,
          prenom: client.prenom,
          dateNaissance: this.formatDate(new Date(client.dateNaissance)),
          telephone: client.telephone,
          email: client.email
        });
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Failed to load client data.';
        this.loading = false;
        console.error(err);
      }
    });
  }

  onSubmit(): void {
    this.submitted = true;

    if (this.clientForm.invalid) {
      return;
    }

    const client: Client = {
      ...this.clientForm.value,
      dateNaissance: new Date(this.clientForm.value.dateNaissance)
    };

    this.loading = true;

    if (this.isEditMode && this.clientId) {
      this.clientService.updateClient(this.clientId, client).subscribe({
        next: () => {
          this.loading = false;
          this.router.navigate(['/clients']);
        },
        error: (err) => {
          this.error = 'Failed to update client. Please try again.';
          this.loading = false;
          console.error(err);
        }
      });
    } else {
      this.clientService.createClient(client).subscribe({
        next: () => {
          this.loading = false;
          this.router.navigate(['/clients']);
        },
        error: (err) => {
          this.error = 'Failed to create client. Please try again.';
          this.loading = false;
          console.error(err);
        }
      });
    }
  }

  private formatDate(date: Date): string {
    const d = new Date(date);
    let month = '' + (d.getMonth() + 1);
    let day = '' + d.getDate();
    const year = d.getFullYear();

    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;

    return [year, month, day].join('-');
  }

  get f() { return this.clientForm.controls; }
} 