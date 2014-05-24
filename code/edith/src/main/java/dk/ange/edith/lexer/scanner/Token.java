package dk.ange.edith.lexer.scanner;

/**
 * A token in the EDIFACT file, can be a control char or a string where the release chars has been parsed
 */
public class Token {

    /**
     * The static Token for the primary separator in a segment, normally a : (colon)
     */
    public static final Token COMPONENT_DATA_ELEMENT_SEPARATOR //
    = new Token(TokenType.COMPONENT_DATA_ELEMENT_SEPARATOR);

    /**
     * The static Token for the secondary separator in a segment, normally a + (plus)
     */
    public static final Token DATA_ELEMENT_SEPARATOR = new Token(TokenType.DATA_ELEMENT_SEPARATOR);

    /**
     * The static Token for the separator between segments, normally a ' (apostrophe)
     */
    public static final Token SEGEMENT_TERMINATOR = new Token(TokenType.SEGEMENT_TERMINATOR);

    private final TokenType type;

    private final String value;

    /**
     * Creates a token of type VALUE
     *
     * @param value
     */
    public Token(final String value) {
        this.type = TokenType.VALUE;
        this.value = value;
    }

    private Token(final TokenType type) {
        this.type = type;
        this.value = null;
    }

    /**
     * @return the type of the token
     */
    public TokenType type() {
        return type;
    }

    /**
     * @return the value of the token. Will be null if the token is not of type VALUE.
     */
    public String value() {
        return value;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Token other = (Token) obj;
        if (type != other.type) {
            return false;
        }
        if (value == null) {
            if (other.value != null) {
                return false;
            }
        } else if (!value.equals(other.value)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        switch (type) {
        case VALUE:
            return "Token(\"" + value + "\")";
        default:
            return "Token(" + type + ")";
        }
    }

    /**
     * The types of tokens
     */
    @SuppressWarnings("hiding")
    public static enum TokenType {
        /**
         * The type of a Token with the value from a simple data element
         */
        VALUE,
        /**
         * The type of the Token for the primary separator in a segment, normally a : (colon)
         */
        COMPONENT_DATA_ELEMENT_SEPARATOR,
        /**
         * The type of the Token for the secondary separator in a segment, normally a + (plus)
         */
        DATA_ELEMENT_SEPARATOR,
        /**
         * The type of the Token for the separator between segments, normally a ' (apostrophe)
         */
        SEGEMENT_TERMINATOR,
    }

}
