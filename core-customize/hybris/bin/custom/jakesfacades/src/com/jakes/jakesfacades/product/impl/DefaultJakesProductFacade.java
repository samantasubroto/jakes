package com.jakes.jakesfacades.product.impl;

import com.jakes.jakesfacades.product.JakesProductFacade;
import de.hybris.platform.commercefacades.product.data.ReviewData;
import de.hybris.platform.commercefacades.product.impl.DefaultProductFacade;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.customerreview.enums.CustomerReviewApprovalType;
import de.hybris.platform.customerreview.model.CustomerReviewModel;
import org.springframework.util.Assert;

public class DefaultJakesProductFacade<REF_TARGET> extends DefaultProductFacade<REF_TARGET> implements JakesProductFacade {

    @Override
    public ReviewData postReview(final String productCode, final ReviewData reviewData)
    {
        Assert.notNull(reviewData, "Parameter reviewData cannot be null.");
        final ProductModel productModel = getProductService().getProductForCode(productCode);
        final UserModel userModel = getUserService().getCurrentUser();
        final CustomerReviewModel customerReviewModel = getCustomerReviewService().createCustomerReview(reviewData.getRating(),
                reviewData.getHeadline(), reviewData.getComment(), userModel, productModel);
        customerReviewModel.setLanguage(getCommonI18NService().getCurrentLanguage());
        customerReviewModel.setAlias(reviewData.getAlias());
        //Jakes changes
        customerReviewModel.setApprovalStatus(CustomerReviewApprovalType.APPROVED);
        getModelService().save(customerReviewModel);
        return getCustomerReviewConverter().convert(customerReviewModel);
    }
}
