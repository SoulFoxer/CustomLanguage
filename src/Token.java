public class Token {
    public enum Type {
        IDENTIFIER, NUMBER, STRING, ASSIGN, SEMICOLON,
        TYPE_INT, TYPE_DOUBLE, TYPE_STRING,
        KEYWORD_VAR, KEYWORD_PRINT, KEYWORD_FUNCTION, LEFT_PAREN, RIGHT_PAREN, COMMA, LEFT_BRACE, RIGHT_BRACE, EQUAL_EQUAL, NOT_EQUAL, LESS_EQUAL, LESS, GREATER_EQUAL, GREATER, PLUS, MINUS, STAR, SLASH, UNKNOWN
    }

    public Type type;
    public String value;

    public Token(Type type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Token{" + "type=" + type + ", value='" + value + "'}";
    }
}
