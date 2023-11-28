import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { PacienteService } from '../services/paciente.service';
import { PacienteModel } from '../model/paciente.model';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-create-paciente',
  templateUrl: './create-paciente.component.html',
  styleUrls: ['./create-paciente.component.css'],
  providers: []

})
export class CreatePacienteComponent {
  paciente = new FormControl('', [Validators.required]);
  @Output() newPacienteEvent = new EventEmitter();
  @Input() idMedico:any = '';

  constructor(private pacienteService: PacienteService, private snackBar: MatSnackBar) {

  }

  public criarNovoPaciente() {
    if (this.paciente.hasError("required")) {
      return;
    }

    let paciente: PacienteModel = {

      nomePaciente: this.paciente.value as string,

    };

    this.pacienteService.createPaciente(this.idMedico, paciente).subscribe(response => {
      this.snackBar.open("Paciente criado com sucesso", "Ok");
      this.newPacienteEvent.emit();
    });
  }

}
