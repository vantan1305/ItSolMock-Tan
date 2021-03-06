import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { AngularBootstrapToastsModule } from 'angular-bootstrap-toasts';
import { NgSpinnerModule } from 'ng-bootstrap-spinner';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastNotificationsModule } from 'ngx-toast-notifications';

import { AppRoutingModule, externalUrlProvider } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { Oauth2Handler } from './oauth2handler';
import { LogoutComponent } from './logout/logout.component';
import { HttpIntercpterService } from './services/http/http-intercpter.service';
import { HomeModule } from './home/home.module';
import { SharedModule } from './shared/shared.module';
import { LoaderInterceptorService } from './services/http/loader-interceptor.service';
import { SocialLoginComponent } from './social-login/social-login.component';
import { ExternalUrlDirective } from './directives/external-url.directive';
import { ActivatedRouteSnapshot } from '@angular/router';
import { NotFoundComponent } from './not-found.component';
import {JWT_OPTIONS, JwtHelperService} from '@auth0/angular-jwt';
import { UserService } from './services/user.service';
import { MatSliderModule } from '@angular/material/slider';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { MatButtonModule } from '@angular/material/button';
import { MatBadgeModule } from '@angular/material/badge';
import { MatInputModule } from '@angular/material/input';
import { MatProgressBarModule } from '@angular/material';



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    Oauth2Handler,
    LogoutComponent,
    SocialLoginComponent,
    ExternalUrlDirective,
    NotFoundComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule,
    AppRoutingModule,
    NgSpinnerModule,
    BrowserAnimationsModule,
    AngularBootstrapToastsModule,
    HomeModule,
    SharedModule,
    HttpClientModule,
    MatSliderModule,
    MatToolbarModule,
    MatIconModule,
    MatBadgeModule,
    MatInputModule,
    MatSidenavModule,
    MatListModule,
    MatButtonModule,
    MatProgressBarModule,
    ToastNotificationsModule.forRoot(
      {
        duration: 6000, type: 'primary',
        position: 'bottom-right',
        preventDuplicates: true
      }
    )
  ],
  providers: [
    UserService,
    JwtHelperService,
    {
      provide: JWT_OPTIONS,
      useValue: JWT_OPTIONS
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpIntercpterService,
      multi: true
    },
    { provide: HTTP_INTERCEPTORS, useClass: HttpIntercpterService, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: LoaderInterceptorService, multi: true },
    {
      provide: externalUrlProvider,
      useValue: (route: ActivatedRouteSnapshot) => {
          const externalUrl = route.paramMap.get('externalUrl');
          window.open(externalUrl, '_self');
      },
  },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
