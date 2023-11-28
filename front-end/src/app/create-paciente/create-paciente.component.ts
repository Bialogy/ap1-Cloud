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
  nomePaciente = new FormControl('', [Validators.required]);
  dtNascimento = new FormControl('', [Validators.required]);
  cpf = new FormControl('', [Validators.required]);
  telefone = new FormControl('', [Validators.required]);
  email = new FormControl('', [Validators.required]);
  @Output() newPacienteEvent = new EventEmitter();
  @Input() idMedico:any = '';

  constructor(private pacienteService: PacienteService, private snackBar: MatSnackBar) {

  }

  public criarNovoPaciente() {
    if (this.nomePaciente.hasError("required")) {
      return;
    }

    if (this.dtNascimento.hasError("required")) {
      return;
    }

    if (this.cpf.hasError("required")) {
      return;
    }

    if (this.telefone.hasError("required")) {
      return;
    }

    if (this.email.hasError("required")) {
      return;
    }

    let paciente: PacienteModel = {

      nomePaciente: this.nomePaciente.value as string,
      dtNascimento: this.dtNascimento.value as string,
      cpf: this.cpf.value as string,
      telefone: this.telefone.value as string,
      email: this.email.value as string,

    };

    this.pacienteService.createPaciente(this.idMedico, paciente).subscribe(response => {
      this.snackBar.open("Paciente criado com sucesso", "Ok");
      this.newPacienteEvent.emit();
    });
  }

}
