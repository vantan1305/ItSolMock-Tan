import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProfileComponent } from './profile/profile.component';
import { HomeComponent } from './home/home.component';
import { HomeRoutingModule } from './home-routing.module';
import { SharedModule } from '../shared/shared.module';
import { SubjectListComponent } from './subject-list/subject-list.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { SettingprofileComponent } from './settingprofile/settingprofile.component';
import { UserService } from '../services/user.service';
import { MatSliderModule } from '@angular/material/slider';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { MatButtonModule } from '@angular/material/button';
import { MatBadgeModule } from '@angular/material/badge';
import { MatInputModule } from '@angular/material/input';
import { PleaseLeaveComponent } from './please-leave/please-leave.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { AdminProjectComponent } from './admin-project/admin-project.component';
import { EditProjectComponent } from './edit-project/edit-project.component';
import { DeleteProjectComponent } from './delete-project/delete-project.component';
<<<<<<< HEAD
import { ProjectListComponent } from './project-list/project-list.component';
import { AddDepartmentComponent } from './add-department/add-department.component';
import { MatDialogModule, MatProgressBarModule, MatRadioModule, MAT_DIALOG_DATA } from '@angular/material';
import { AddProjectToDepartmentComponent } from './add-project-to-department/add-project-to-department.component';
import { DisplayDepartmentComponent } from './display-department/display-department.component';
import { DisplayProjectComponent } from './display-project/display-project.component';
import { ListDepartmentComponent } from './list-department/list-department.component';
import { EditDepartmentComponent } from './edit-department/edit-department.component';
import { DeleteDepartmentComponent } from './delete-department/delete-department.component';
import { ManagerUserComponent } from './manager-user/manager-user.component';
import { DeleteUserComponent } from './delete-user/delete-user.component';
import {MatSelectModule} from '@angular/material/select';
import { AdminEditUserComponent } from './admin-edit-user/admin-edit-user.component';
import { AdminAddUserComponent } from './admin-add-user/admin-add-user.component';
=======

>>>>>>> de475c2b74b9a33b9fa23fd4eac9c1511f8091c5
@NgModule({
  declarations: [
    ProfileComponent,
    HomeComponent,
    SubjectListComponent,
    SettingprofileComponent,
    PleaseLeaveComponent,
<<<<<<< HEAD
    AdminProjectComponent,
    EditProjectComponent,
    DeleteProjectComponent,
    ProjectListComponent,
    AddDepartmentComponent,
    AddProjectToDepartmentComponent,
    DisplayDepartmentComponent,
    DisplayProjectComponent,
    ListDepartmentComponent,
    EditDepartmentComponent,
    DeleteDepartmentComponent,
    ManagerUserComponent,
    DeleteUserComponent,
    AdminEditUserComponent,
    AdminAddUserComponent,
=======
    PleaseLeaveComponent,
    AdminProjectComponent,
    EditProjectComponent,
    DeleteProjectComponent,
>>>>>>> de475c2b74b9a33b9fa23fd4eac9c1511f8091c5

  ],
  imports: [
    CommonModule,
    HomeRoutingModule,
    SharedModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatSliderModule,
    MatToolbarModule,
    MatIconModule,
    MatBadgeModule,
    MatInputModule,
    MatSidenavModule,
    MatListModule,
    MatButtonModule,
    NgxPaginationModule,
<<<<<<< HEAD
    MatProgressBarModule,
    MatRadioModule,
    MatDialogModule,
    MatSelectModule
=======

>>>>>>> de475c2b74b9a33b9fa23fd4eac9c1511f8091c5
  ],
  providers:[
    UserService
  ]
})
export class HomeModule { }
