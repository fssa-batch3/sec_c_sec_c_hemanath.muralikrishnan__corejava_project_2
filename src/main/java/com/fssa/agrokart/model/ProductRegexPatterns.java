/**
 * Regular expression patterns for validating fields in a product model.
 *
 * @author HemanathMuralikrishnan
 */
package com.fssa.agrokart.model;

public final class ProductRegexPatterns {

    /**
     * Pattern to validate the English name of the product.
     * <p>
     * Explanation:
     * ^            - Start of the string.
     * (?!.*\\s{2,}) - Negative lookahead to ensure no consecutive spaces.
     * [A-Za-z ]    - Allow letters (both uppercase and lowercase) and spaces.
     * {2,50}       - Minimum of 2 and maximum of 50 characters.
     * $            - End of the string.
     */
    public static final String ENGLISH_NAME = "^(?!.*\\s{2,})[A-Za-z ]{2,50}$";

    /**
     * Pattern to validate the Tamil name of the product.
     * <p>
     * Explanation:
     * ^(?!\\s)        - Start of the string and ensure it's not a space.
     * (?!.*\\s{2})    - Negative lookahead to ensure no consecutive spaces.
     * [\\p{IsTamil}. ] - Allow Tamil characters, dots, and spaces.
     */
    public static final String TAMIL_NAME = "^(?!\\s)(?!.*\\s{2})[\\p{IsTamil}. ]+";

    /**
     * Pattern to validate the image URL of the product.
     * <p>
     * Explanation:
     * (?i)                  - Case-insensitive matching.
     * \\b                   - Word boundary.
     * ((https?|ftp)://)?    - Optional http/https/ftp protocol.
     * [a-z0-9-]+            - Alphanumeric characters and hyphens.
     * (\\.[a-z0-9-]+)+      - One or more occurrences of dot and alphanumeric.
     * ([/?].*)?             - Optional slash and anything afterwards.
     * \\.(jpg|jpeg|gif|png|bmp)\\b - File extensions: jpg, jpeg, gif, png, bmp.
     */
    public static final String IMAGE_URL = "(?i)\\b((https?|ftp)://)?[a-z0-9-]+(\\.[a-z0-9-]+)+([/?].*)?\\.(jpg|jpeg|gif|png|bmp)\\b";

    /**
     * Pattern to validate the description of the product.
     * <p>
     * Explanation:
     * ^             - Start of the string.
     * [\u0020-\u007E\n\r] - Allow printable ASCII characters, line feeds, and carriage returns.
     * {10,}         - Minimum of 10 characters.
     * $             - End of the string.
     */
    public static final String DESCRIPTION = "^[\u0020-\u007E\n\r]{10,}$";

    /**
     * Private constructor to prevent instantiation of the {@code ProductRegexPatterns} class.
     */
    private ProductRegexPatterns() {
    }
}
