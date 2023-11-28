import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Medico } from '../model/medico.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MedicoService {

  constructor(private httpClient: HttpClient) { }

  public getMedicos(): Observable<Medico[]> {
    return this.httpClient.get<Medico[]>("https://ibmec-cloud-ap1.azurewebsites.net/medico");
  }

  public getMedicoById(id: any): Observable<Medico> {
    return this.httpClient.get<Medico>("https://ibmec-cloud-ap1.azurewebsites.net/medico/" + id);
  }

}
