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
            // Handle strings
            else if (current == '"') {
                tokens.add(readString());
            }
            // Handle operators and punctuation
            else if (current == '=') {
                if (peek() == '=') {
                    tokens.add(new Token(Token.Type.EQUAL_EQUAL, "=="));
                    position += 2;
                } else {
                    tokens.add(new Token(Token.Type.ASSIGN, "="));
                    position++;
                }
            } else if (current == '!') {
                if (peek() == '=') {
                    tokens.add(new Token(Token.Type.NOT_EQUAL, "!="));
                    position += 2;
                }
            } else if (current == '<') {
                if (peek() == '=') {
                    tokens.add(new Token(Token.Type.LESS_EQUAL, "<="));
                    position += 2;
                } else {
                    tokens.add(new Token(Token.Type.LESS, "<"));
                    position++;
                }
            } else if (current == '>') {
                if (peek() == '=') {
                    tokens.add(new Token(Token.Type.GREATER_EQUAL, ">="));
                    position += 2;
                } else {
                    tokens.add(new Token(Token.Type.GREATER, ">"));
                    position++;
                }
            } else if (current == ';') {
                tokens.add(new Token(Token.Type.SEMICOLON, ";"));
                position++;
            } else if (current == '(') {
                tokens.add(new Token(Token.Type.LEFT_PAREN, "("));
                position++;
            } else if (current == ')') {
                tokens.add(new Token(Token.Type.RIGHT_PAREN, ")"));
                position++;
            } else if (current == '{') {
                tokens.add(new Token(Token.Type.LEFT_BRACE, "{"));
                position++;
            } else if (current == '}') {
                tokens.add(new Token(Token.Type.RIGHT_BRACE, "}"));
                position++;
            } else if (current == ',') {
                tokens.add(new Token(Token.Type.COMMA, ","));
                position++;
            } else if (current == '+') {
                tokens.add(new Token(Token.Type.PLUS, "+"));
                position++;
            } else if (current == '-') {
                tokens.add(new Token(Token.Type.MINUS, "-"));
                position++;
            } else if (current == '*') {
                tokens.add(new Token(Token.Type.STAR, "*"));
                position++;
            } else if (current == '/') {
                tokens.add(new Token(Token.Type.SLASH, "/"));
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

        return switch (word) {
            case "var" -> new Token(Token.Type.KEYWORD_VAR, word);
            case "print" -> new Token(Token.Type.KEYWORD_PRINT, word);
            case "function" -> new Token(Token.Type.KEYWORD_FUNCTION, word);
            default -> new Token(Token.Type.IDENTIFIER, word);
        };
    }

    private Token readNumber() {
        StringBuilder builder = new StringBuilder();
        while (position < input.length() && Character.isDigit(input.charAt(position))) {
            builder.append(input.charAt(position));
            position++;
        }
        return new Token(Token.Type.NUMBER, builder.toString());
    }

    private Token readString() {
        position++; // Skip first "
        StringBuilder builder = new StringBuilder();
        while (position < input.length() && input.charAt(position) != '"') {
            builder.append(input.charAt(position));
            position++;
        }
        position++; // Skip closing "
        return new Token(Token.Type.STRING, builder.toString());
    }

    private char peek() {
        if (position + 1 >= input.length()) return '\0';
        return input.charAt(position + 1);
    }
}
