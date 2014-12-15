package org.shop;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * The main Data Initializer util class.
 */
@Component
public class DataInitializer {

    /** The seller initializer. */
    @Resource
    private SellerInitializer sellerInitializer;
    
    /** The product initializer. */
    @Resource
    private ProductInitializer productInitializer;
    
    /** The proposal initializer. */
    @Autowired @Qualifier("proposalInitializer")
    private ProposalInitializer proposalInitializer;
    
    /** The user initializer. */
    @Resource
    private UserInitializer userInitializer;

    /**
     * Inits the data.
     */

    @Autowired
    public void initData() {
        sellerInitializer.initSellers();
        userInitializer.initUsers();
        productInitializer.initProducts();
        proposalInitializer.initProposals();
    }
    
    /**
     * Sets the seller initializer.
     *
     * @param sellerInitializer the new seller initializer
     */
    public void setSellerInitializer(SellerInitializer sellerInitializer) {
        this.sellerInitializer = sellerInitializer;
    }

    /**
     * Sets the product initializer.
     *
     * @param productInitializer the new product initializer
     */
    public void setProductInitializer(ProductInitializer productInitializer) {
        this.productInitializer = productInitializer;
    }

    /**
     * Sets the proposal initializer.
     *
     * @param proposalInitializer the new proposal initializer
     */
    public void setProposalInitializer(ProposalInitializer proposalInitializer) {
        this.proposalInitializer = proposalInitializer;
    }

    /**
     * Sets the user initializer.
     *
     * @param userInitializer the new user initializer
     */
    public void setUserInitializer(UserInitializer userInitializer) {
        this.userInitializer = userInitializer;
    }
}
