import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { JakesProductIntroComponent } from './jakes-product-intro.component';
import { IconModule, ProductIntroModule, StarRatingModule } from '@spartacus/storefront';
import { CmsConfig, I18nModule, provideDefaultConfig } from '@spartacus/core';

@NgModule({
  imports: [
    CommonModule, 
    I18nModule, 
    StarRatingModule,
    IconModule
  ],
  providers: [
    provideDefaultConfig(<CmsConfig>{
      cmsComponents: {
        ProductIntroComponent: {
          component: JakesProductIntroComponent,
        },
      },
    }),
  ],
  declarations: [JakesProductIntroComponent],
  exports: [JakesProductIntroComponent],
})
export class JakesProductIntroModule extends ProductIntroModule { }
