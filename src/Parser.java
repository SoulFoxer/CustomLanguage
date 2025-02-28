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
}
