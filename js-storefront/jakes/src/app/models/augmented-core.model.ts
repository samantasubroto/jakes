import '@spartacus/checkout/base/root';
import '@spartacus/cart/base/root';
import '@spartacus/storefront';
import '@spartacus/core'
import { Price, VariantType } from '@spartacus/core';

declare module '@spartacus/core' {
  export interface Product {
    liked?: boolean
  }

  export interface Stock {
    expirationDate?: Date;
    lastInStockDate?: Date;
  }

  interface VariantOption {
    liked?: boolean;
  }
}