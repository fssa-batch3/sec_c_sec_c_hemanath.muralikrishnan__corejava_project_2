package com.fssa.agrokart.validator;

/**
 * 
 * @author HemanathMuralikrishn
 * 
 *         this interface holds the different regex patterns
 *
 */

public interface RegexPatterns {

//	to validate the product English name
	public static final String ENGLISH_NAME = "^(?!.*\\s{2,})[A-Za-z ]{2,50}$";

//	to validate the product Tamil name
	public static final String TAMIL_NAME = "^(?!\\s)(?!.*\\s{2})[\\p{IsTamil}. ]+";

//	to validate the image url
	public static final String IMAGE_URL = "(?i)\\b((https?|ftp)://)?[a-z0-9-]+(\\.[a-z0-9-]+)+([/?].*)?\\.(jpg|jpeg|gif|png|bmp)\\b";

//	to validate the description
	public static final String DESCRIPTION = "^[\u0020-\u007E\n\r]{10,}$";

}