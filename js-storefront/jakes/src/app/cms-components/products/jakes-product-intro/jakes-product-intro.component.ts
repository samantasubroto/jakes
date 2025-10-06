import { Component } from '@angular/core';
import { EventService, Product, Stock, TranslationService, VariantOption, WindowRef } from '@spartacus/core';
import { CurrentProductService, ICON_TYPE, ProductIntroComponent } from '@spartacus/storefront';
import { Observable } from 'rxjs';
import { JAKES_ICON_TYPE } from 'src/app/models';
import { JakesProductService } from 'src/app/services';

@Component({
  selector: 'cx-jakes-product-intro',
  templateUrl: './jakes-product-intro.component.html',
  styleUrls: ['./jakes-product-intro.component.scss']
})
export class JakesProductIntroComponent extends ProductIntroComponent {
  iconTypes = JAKES_ICON_TYPE;

  constructor(
    protected override currentProductService: CurrentProductService,
    protected override translationService: TranslationService,
    protected override winRef: WindowRef,
    protected override eventService: EventService,
    protected jakesProductService: JakesProductService
  ) { 
    super(currentProductService, translationService, winRef, eventService);
  }

  toggleLike(product: any): void {
    product.liked = !product?.liked;

    // Call backend to persist the change
    this.jakesProductService.updateProductLikedStatus(product.code, product.liked)
      .subscribe({
        next: () => console.log(`Product ${product.code} liked: ${product.liked}`),
        error: err => console.error('Failed to update liked status', err)
      });
  }
}