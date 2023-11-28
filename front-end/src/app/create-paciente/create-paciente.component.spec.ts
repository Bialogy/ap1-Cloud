import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatePacienteComponent } from './create-paciente.component';

describe('CreatePacienteComponent', () => {
  let component: CreatePacienteComponent;
  let fixture: ComponentFixture<CreatePacienteComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CreatePacienteComponent]
    });
    fixture = TestBed.createComponent(CreatePacienteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
