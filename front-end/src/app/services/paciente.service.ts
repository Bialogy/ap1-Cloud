import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PacienteModel } from '../model/paciente.model';

@Injectable({
  providedIn: 'root'
})
export class PacienteService {

  constructor(private http: HttpClient) { }

  public getPaciente(idMedico:any) : Observable<PacienteModel[]> {
    return this.http.get<PacienteModel[]>(`https://ibmec-cloud-ap1.azurewebsites.net/medico/${idMedico}/paciente`);
  }

  public createPaciente(idMedico: any, paciente: PacienteModel): Observable<PacienteModel> {
    return this.http.post<PacienteModel>(`https://ibmec-cloud-ap1.azurewebsites.net/medico/${idMedico}/paciente`, paciente);
  }
}
