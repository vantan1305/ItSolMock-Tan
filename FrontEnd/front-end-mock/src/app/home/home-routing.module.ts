import { NgModule } from '@angular/core';
import { Routes, RouterModule, RouterEvent } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { SubjectListComponent } from './subject-list/subject-list.component';
import { RouteGuardService } from '../services/route-guard.service';
import { AdminComponent } from '../shared/admin/admin.component';
import { SettingprofileComponent } from './settingprofile/settingprofile.component';
import { PleaseLeaveComponent } from './please-leave/please-leave.component';
import { ManagerComponent } from '../shared/manager/manager.component';
import { PmComponent } from '../shared/pm/pm.component';
import { AdminProjectComponent } from './admin-project/admin-project.component';
import { EditProjectComponent } from './edit-project/edit-project.component';
import { DeleteProjectComponent } from './delete-project/delete-project.component';
<<<<<<< HEAD
import { ProjectListComponent } from './project-list/project-list.component';
import { AddDepartmentComponent } from './add-department/add-department.component';
import { AddProjectToDepartmentComponent } from './add-project-to-department/add-project-to-department.component';
import { DisplayDepartmentComponent } from './display-department/display-department.component';
import { DisplayProjectComponent } from './display-project/display-project.component';
import { ListDepartmentComponent } from './list-department/list-department.component';
import { EditDepartmentComponent } from './edit-department/edit-department.component';
import { DeleteDepartmentComponent } from './delete-department/delete-department.component';
import { ManagerUserComponent } from './manager-user/manager-user.component';
import { AdminEditUserComponent } from './admin-edit-user/admin-edit-user.component';
import { AdminAddUserComponent } from './admin-add-user/admin-add-user.component';

=======
>>>>>>> de475c2b74b9a33b9fa23fd4eac9c1511f8091c5

export const Home_Module_routes: Routes = [

    { path: 'profile', component: ProfileComponent , canActivate: [RouteGuardService]},
    { path: 'setting', component: SettingprofileComponent , canActivate: [RouteGuardService]}, //, canActivate: [RouteGuardService]
    { path: 'subjects', component: SubjectListComponent , canActivate: [RouteGuardService]},
    {path:'admin', component:AdminComponent,canActivate:[RouteGuardService]},

    {path:'manager', component:ManagerComponent,canActivate:[RouteGuardService]},
    {path:'pm', component:PmComponent,canActivate:[RouteGuardService]},
    {path:'managerProject', component:AdminProjectComponent,canActivate:[RouteGuardService]},
    {path:'managerProject/edit', children: [ {path: ':id', component: EditProjectComponent}],canActivate:[RouteGuardService]},
    {path:'managerProject/deleteProject', component:DeleteProjectComponent,canActivate:[RouteGuardService]},
<<<<<<< HEAD
    {path:'managerProject/list', component:ProjectListComponent,canActivate:[RouteGuardService]},
    {path:'listDepartment', component:ListDepartmentComponent,canActivate:[RouteGuardService]},
    {path:'listProject', component:ProjectListComponent,canActivate:[RouteGuardService]},
    {path:'addDepartment', component:AddDepartmentComponent,canActivate:[RouteGuardService]},
    {path:'displayDipartment', children: [ {path: ':id', component: DisplayDepartmentComponent}],canActivate:[RouteGuardService]},
    {path:'department/edit', children: [ {path: ':id', component: EditDepartmentComponent}],canActivate:[RouteGuardService]},
    {path:'department/deleteDepartment', component:DeleteDepartmentComponent,canActivate:[RouteGuardService]},
    {path:'managerUser', component:ManagerUserComponent,canActivate:[RouteGuardService]},
    {path:'pleaseLeave', component:PleaseLeaveComponent,canActivate:[RouteGuardService]},
    {path:'managerUser/edit', children: [ {path: ':id', component: AdminEditUserComponent}],canActivate:[RouteGuardService]},
    {path:'adminAddUser', component:AdminAddUserComponent,canActivate:[RouteGuardService]},
=======
>>>>>>> de475c2b74b9a33b9fa23fd4eac9c1511f8091c5
]

@NgModule({
    imports: [
        RouterModule.forChild(Home_Module_routes)
    ],
    exports: [
        RouterModule
    ]
})
export class HomeRoutingModule {

}
