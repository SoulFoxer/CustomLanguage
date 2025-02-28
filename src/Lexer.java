import java.util.ArrayList;
import java.util.List;

public class Lexer {
    private final String input;
    private int position;

    public Lexer(String input) {
        this.input = input;
        this.position = 0;
    }

    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();
        while (position < input.length()) {
            char current = input.charAt(position);

            // Skip whitespace characters
            if (Character.isWhitespace(current)) {
                position++;
            }
            // Handle identifiers and keywords
            else if (Character.isLetter(current)) {
                tokens.add(readIdentifier());
            }
            // Handle numbers
            else if (Character.isDigit(current)) {
                tokens.add(readNumber());
            }
            // Handle operators and punctuation
            else if (current == '=') {
                tokens.add(new Token(Token.Type.ASSIGN, "="));
                position++;
            } else if (current == ';') {
                tokens.add(new Token(Token.Type.SEMICOLON, ";"));
                position++;
            } else {
                // If the character doesn't match any valid token type
                tokens.add(new Token(Token.Type.UNKNOWN, String.valueOf(current)));
                position++;
            }
        }
        return tokens;
    }

    private Token readIdentifier() {
        StringBuilder builder = new StringBuilder();
        while (position < input.length() && (Character.isLetterOrDigit(input.charAt(position)))) {
            builder.append(input.charAt(position));
            position++;
        }
        String word = builder.toString();

        // Return corresponding token for keyword or identifier
        if (word.equals("var")) {
            return new Token(Token.Type.KEYWORD_VAR, word);
        } else if (word.equals("print")) {
            return new Token(Token.Type.KEYWORD_PRINT, word);
        } else {
            return new Token(Token.Type.IDENTIFIER, word);
        }
    }

    private Token readNumber() {
        StringBuilder builder = new StringBuilder();
        while (position < input.length() && Character.isDigit(input.charAt(position))) {
            builder.append(input.charAt(position));
            position++;
        }
        return new Token(Token.Type.NUMBER, builder.toString());
    }
}
