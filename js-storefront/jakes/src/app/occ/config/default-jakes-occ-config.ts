import { OccConfig } from "@spartacus/core";

export const jakesOccConfig: OccConfig = {
  backend: {
    occ: {
      endpoints: {
        product: {
          default:
            'products/${productCode}?fields=DEFAULT,averageRating,images(FULL),classifications,manufacturer,numberOfReviews,categories(FULL),baseOptions,baseProduct,variantOptions,variantType,liked',
          list: 'products/${productCode}?fields=code,name,summary,price(formattedValue),images(DEFAULT,galleryIndex),baseProduct,liked',
          details:
            'products/${productCode}?fields=averageRating,stock(DEFAULT),description,availableForPickup,code,url,price(DEFAULT),numberOfReviews,manufacturer,categories(FULL),priceRange,multidimensional,tags,images(FULL),liked',
          attributes: 'products/${productCode}?fields=classifications',
          price: 'products/${productCode}?fields=price(formattedValue)',
          stock: 'products/${productCode}?fields=stock(DEFAULT)',
        }
      }
    }
  }
}