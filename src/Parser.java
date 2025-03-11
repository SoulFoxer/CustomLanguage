import statements.FunctionDeclarationStatement;
import statements.PrintStatement;
import statements.Statement;
import statements.VarDeclaration;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private final List<Token> tokens;
    private int position = 0;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public List<Statement> parse() {
        List<Statement> statements = new ArrayList<>();
        while (position < tokens.size()) {
            statements.add(parseStatement());
        }
        return statements;
    }

    private Statement parseStatement() {
        Token token = tokens.get(position);
        if (token.type == Token.Type.KEYWORD_VAR) {
            return parseVarDeclaration();
        } else if (token.type == Token.Type.KEYWORD_PRINT) {
            return parsePrintStatement();
        } else if (token.type == Token.Type.KEYWORD_FUNCTION) {
            return parseFunctionDeclaration();
        }
        throw new RuntimeException("Unbekannte Anweisung: " + token);
    }

    private Statement parseVarDeclaration() {
        consume(Token.Type.KEYWORD_VAR);
        Token name = consume(Token.Type.IDENTIFIER);
        consume(Token.Type.ASSIGN);
        Token value = consume(Token.Type.NUMBER);
        consume(Token.Type.SEMICOLON);
        return new VarDeclaration(name.value, Integer.parseInt(value.value));
    }

    private Statement parsePrintStatement() {
        consume(Token.Type.KEYWORD_PRINT);
        Token name = consume(Token.Type.IDENTIFIER);
        consume(Token.Type.SEMICOLON);
        System.out.println("parsed name: " + name.value);
        return new PrintStatement(name.value);
    }

    private Token consume(Token.Type type) {
        Token token = tokens.get(position);
        if (token.type != type) {
            throw new RuntimeException("Erwartet " + type + ", aber gefunden: " + token.type);
        }
        position++;
        return token;
    }

    private Statement parseFunctionDeclaration() {
        consume(Token.Type.KEYWORD_FUNCTION);
        Token name = consume(Token.Type.IDENTIFIER);
        consume(Token.Type.LEFT_PAREN);
        List<String> parameters = new ArrayList<>();
        while (tokens.get(position).type != Token.Type.RIGHT_PAREN) {
            final Token token = consume(Token.Type.IDENTIFIER);
            parameters.add(token.value);
            if (tokens.get(position).type == Token.Type.COMMA) {
                position++;
            }
        }
        consume(Token.Type.RIGHT_PAREN);
        consume(Token.Type.LEFT_BRACE);
        List<Statement> body = new ArrayList<>();
        while (tokens.get(position).type != Token.Type.RIGHT_BRACE) {
            body.add(parseStatement());
        }
        consume(Token.Type.RIGHT_BRACE);
        return new FunctionDeclarationStatement(name.value, parameters, body);
    }

}
