import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddProjectToDepartmentComponent } from './add-project-to-department.component';

describe('AddProjectToDepartmentComponent', () => {
  let component: AddProjectToDepartmentComponent;
  let fixture: ComponentFixture<AddProjectToDepartmentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddProjectToDepartmentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddProjectToDepartmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
