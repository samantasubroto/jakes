package com.jakes.jakesfacades.product;

import de.hybris.platform.cmsfacades.data.ProductData;
import de.hybris.platform.commercefacades.product.ProductFacade;

public interface JakesProductFacade extends ProductFacade {

    void updateProductLikedStatus(final String productCode, final Boolean isLiked);
}
