package com.jakes.jakesfacades.product.impl;

import com.jakes.jakesfacades.product.JakesProductFacade;
import de.hybris.platform.b2b.jalo.B2BCustomer;
import de.hybris.platform.b2b.model.B2BCustomerModel;
import de.hybris.platform.cmsfacades.data.ProductData;
import de.hybris.platform.commercefacades.product.data.ReviewData;
import de.hybris.platform.commercefacades.product.impl.DefaultProductFacade;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.customerreview.enums.CustomerReviewApprovalType;
import de.hybris.platform.customerreview.model.CustomerReviewModel;
import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.Set;

public class DefaultJakesProductFacade<REF_TARGET> extends DefaultProductFacade<REF_TARGET> implements JakesProductFacade {

    @Override
    public ReviewData postReview(final String productCode, final ReviewData reviewData) {
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

    @Override
    public void updateProductLikedStatus(final String productCode, final Boolean isLiked) {
        Assert.notNull(productCode, "Parameter productCode cannot be null.");
        final ProductModel productModel = getProductService().getProductForCode(productCode);
        final UserModel userModel = getUserService().getCurrentUser();
        final Set<B2BCustomerModel> likedBy = new HashSet<>(productModel.getLikedProducts());
        if (Boolean.TRUE.equals(isLiked)) {
            likedBy.add((B2BCustomerModel) userModel);
        } else {
            likedBy.remove((B2BCustomerModel) userModel);
        }
        productModel.setLikedProducts(likedBy);
        getModelService().save(productModel);
    }
}
