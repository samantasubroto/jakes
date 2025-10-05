package com.jakes.jakesfacades.product.converters.populators;

import de.hybris.platform.b2b.services.B2BCustomerService;
import de.hybris.platform.commercefacades.product.converters.populator.AbstractProductPopulator;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commerceservices.url.UrlResolver;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.springframework.util.Assert;

public class JakesProductPopulator<SOURCE extends ProductModel, TARGET extends ProductData> extends AbstractProductPopulator<SOURCE, TARGET> {

    private UrlResolver<ProductModel> productModelUrlResolver;
    private Populator<ProductModel, ProductData> productBasicPopulator;
    private Populator<ProductModel, ProductData> variantSelectedPopulator;
    private Populator<ProductModel, ProductData> productPrimaryImagePopulator;
    private B2BCustomerService b2BCustomerService;

    @Override
    public void populate(SOURCE source, TARGET target) throws ConversionException {
        Assert.notNull(source, "Parameter source cannot be null.");
        Assert.notNull(target, "Parameter target cannot be null.");

        target.setCode(source.getCode());
        target.setName(source.getName());
        target.setUrl(getProductModelUrlResolver().resolve(source));
        getProductBasicPopulator().populate(source, target);
        getVariantSelectedPopulator().populate(source, target);
        getProductPrimaryImagePopulator().populate(source, target);

        hasProductLikedByCustoner(source, target);
    }

    protected void hasProductLikedByCustoner(SOURCE source, TARGET target) {
        target.setLiked(Boolean.TRUE.equals(source.getLikedProducts().contains(getB2BCustomerService().getCurrentB2BCustomer())));
    }

    public UrlResolver<ProductModel> getProductModelUrlResolver() {
        return productModelUrlResolver;
    }

    public void setProductModelUrlResolver(UrlResolver<ProductModel> productModelUrlResolver) {
        this.productModelUrlResolver = productModelUrlResolver;
    }

    public Populator<ProductModel, ProductData> getProductBasicPopulator() {
        return productBasicPopulator;
    }

    public void setProductBasicPopulator(Populator<ProductModel, ProductData> productBasicPopulator) {
        this.productBasicPopulator = productBasicPopulator;
    }

    public Populator<ProductModel, ProductData> getVariantSelectedPopulator() {
        return variantSelectedPopulator;
    }

    public void setVariantSelectedPopulator(Populator<ProductModel, ProductData> variantSelectedPopulator) {
        this.variantSelectedPopulator = variantSelectedPopulator;
    }

    public Populator<ProductModel, ProductData> getProductPrimaryImagePopulator() {
        return productPrimaryImagePopulator;
    }

    public void setProductPrimaryImagePopulator(Populator<ProductModel, ProductData> productPrimaryImagePopulator) {
        this.productPrimaryImagePopulator = productPrimaryImagePopulator;
    }

    public B2BCustomerService getB2BCustomerService() {
        return b2BCustomerService;
    }

    public void setB2BCustomerService(B2BCustomerService b2BCustomerService) {
        this.b2BCustomerService = b2BCustomerService;
    }
}
