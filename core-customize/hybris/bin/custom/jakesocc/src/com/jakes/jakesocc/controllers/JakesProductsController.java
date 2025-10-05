package com.jakes.jakesocc.controllers;

import com.jakes.jakesfacades.product.JakesProductFacade;
import de.hybris.platform.b2bocc.v2.controllers.BaseController;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.webservicescommons.swagger.ApiBaseSiteIdParam;
import de.hybris.platform.webservicescommons.swagger.ApiFieldsParam;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@Tag(name = "Products")
@RequestMapping(value = "/{baseSiteId}/products")
public class JakesProductsController extends BaseController {
    private static final String CONSIDER_FIELDS_KEY = "toggle.occ.retrieving.product.performance.improvement.enabled";
    private static final Logger LOG = LoggerFactory.getLogger(JakesProductsController.class);

    @Resource(name = "configurationService")
    private ConfigurationService configurationService;
    @Resource(name = "jakesProductFacade")
    private JakesProductFacade jakesProductFacade;


    @PatchMapping("/liked/{productCode}")
    @ResponseBody
    @ApiBaseSiteIdParam
    @ResponseStatus(HttpStatus.OK)
    public void setLikedProductStatus(
            @Parameter(description = "Liked Status", required = true) @RequestParam Boolean isLiked,
            @Parameter(description = "Product identifier.", required = true) @PathVariable final String productCode,
            @ApiFieldsParam @RequestParam final String fields) {
        jakesProductFacade.updateProductLikedStatus(productCode, isLiked);
    }

//    @GetMapping("/{productCode}")
//    @ResponseBody
//    @Operation(operationId = "getProduct", summary = "Retrieves product details.", description = "Retrieves the details of a single product using the product identifier.")
//    @ApiBaseSiteIdParam
//    @RequestMappingOverride(priorityProperty = "jakesocc.Products.getProduct.priority=99")
//    public ProductWsDTO getProduct(
//            @Parameter(description = "Product identifier.", required = true) @PathVariable final String productCode,
//            @ApiFieldsParam @RequestParam final String fields) {
//        boolean isConsiderFields = configurationService.getConfiguration().getBoolean(CONSIDER_FIELDS_KEY, false);
//
//        final ProductData product = productFacade.getProductForCodeAndOptions(productCode, null);
//        return getDataMapper().map(product, ProductWsDTO.class, fields);
//    }
}
