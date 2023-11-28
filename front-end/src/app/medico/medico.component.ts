import { AfterViewInit, Component, OnDestroy, OnInit } from '@angular/core';
import { MedicoService } from '../services/medico.service';
import { Medico } from '../model/medico.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-medico',
  templateUrl: './medico.component.html',
  styleUrls: ['./medico.component.css'],
})
export class MedicoComponent implements OnInit{
  medicos: Medico[] = [];

  constructor(private medicoService: MedicoService, private router: Router) {

  }

  ngOnInit(): void {
    this.medicoService.getMedicos().subscribe(response => {
      this.medicos = response;
    });
  }

  redirectToDetail(id: any) {
      this.router.navigate(["detail", id]);
  }

  //CICLO DO VIDA DO COMPONENTE DO ANGULAR
  /*
  ngBeforeViewInit(): void {

  }

  ngAfterViewInit(): void {

  }

  ngChanges(): void {

  }

  ngOnDestroy(): void {

  }
  */
}
