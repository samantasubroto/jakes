import { Component } from '@angular/core';
import { Product, Stock, VariantOption } from '@spartacus/core';
import { ICON_TYPE, ProductIntroComponent } from '@spartacus/storefront';
import { Observable } from 'rxjs';
import { JAKES_ICON_TYPE } from 'src/app/models';

@Component({
  selector: 'cx-jakes-product-intro',
  templateUrl: './jakes-product-intro.component.html',
  styleUrls: ['./jakes-product-intro.component.scss']
})
export class JakesProductIntroComponent extends ProductIntroComponent{
  override product$: Observable<any> =
    this.currentProductService.getProduct();

    value:Stock| null = null;
    iconTypes = JAKES_ICON_TYPE;

    ngOnInit() {
      this.currentProductService.getProduct().subscribe(value => console.log(value));
    }

    toggleLike(product: any): void {
    product.liked = !product?.liked;

    // Call backend to persist like status
    // Example:
    // this.productService.updateProductLikedStatus(product.code, product.liked).subscribe();

    // For demo purposes, just log it
    console.log(`Product ${product.code} liked: ${product?.liked}`);
  }
}
