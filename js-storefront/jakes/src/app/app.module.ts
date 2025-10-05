import { JakesParagraphModule } from './components/jakes-paragraph/jakes-paragraph.module';
import { HttpClientModule } from "@angular/common/http";
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { EffectsModule } from "@ngrx/effects";
import { StoreModule } from "@ngrx/store";
import { AppRoutingModule } from "@spartacus/storefront";
import { AppComponent } from './app.component';
import { SpartacusModule } from './spartacus/spartacus.module';
import { JakesOccModule } from './occ';
import { JakesProductIntroModule } from './cms-components';
import { provideConfig } from '@spartacus/core';
import { jakesIconConfig } from './config';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    StoreModule.forRoot({}),
    EffectsModule.forRoot([]),
    SpartacusModule,
    JakesParagraphModule,
    JakesOccModule,
    JakesProductIntroModule
  ],
  providers: [
     provideConfig(jakesIconConfig),
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
