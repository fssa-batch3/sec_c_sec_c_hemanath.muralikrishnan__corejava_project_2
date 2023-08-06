package com.fssa.agrokart.regexpattern;

/**
 * An interface which holds the different regex patterns for a product model object
 *
 * @author HemanathMuralikrishnan
 */

public interface ProductRegexPatterns {

    //	to validate the product English name
    String ENGLISH_NAME = "^(?!.*\\s{2,})[A-Za-z ]{2,50}$";

    //	to validate the product Tamil name
    String TAMIL_NAME = "^(?!\\s)(?!.*\\s{2})[\\p{IsTamil}. ]+";

    //	to validate the image url
    String IMAGE_URL = "(?i)\\b((https?|ftp)://)?[a-z0-9-]+(\\.[a-z0-9-]+)+([/?].*)?\\.(jpg|jpeg|gif|png|bmp)\\b";

    //	to validate the description
    String DESCRIPTION = "^[\u0020-\u007E\n\r]{10,}$";

}
