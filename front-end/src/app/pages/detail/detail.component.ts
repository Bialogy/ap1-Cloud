import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Medico } from 'src/app/model/medico.model';
import { PacienteService } from 'src/app/services/paciente.service';
import { MedicoService } from 'src/app/services/medico.service';
import { PacienteModel } from 'src/app/model/paciente.model';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent implements OnInit {

  medico?: Medico;
  paciente?: PacienteModel[];
  showCriarPaciente = false;

  constructor(private medicoService: MedicoService,
    private pacienteService: PacienteService,
    private route: ActivatedRoute) {

  }
  ngOnInit(): void {

    let idMedico = this.route.snapshot.params["idMedico"];
    this.medicoService.getMedicoById(idMedico).subscribe(response => {
      this.medico = response;
    });

    this.carregaPaciente();
  }

  private carregaPaciente() {
    let idMedico = this.route.snapshot.params["idMedico"];
    this.pacienteService.getPaciente(idMedico).subscribe(response => {
      this.paciente = response;
    });
  }

  public mostrarCriarPaciente() {
    this.showCriarPaciente = true;
  }

  public atualizarPacientes() {
    this.carregaPaciente();
  }

}
