package parser;

import lexer.Token;
import lexer.TokenType;

import java.util.List;
import java.util.ListIterator;

public class JsonParser {
    public boolean parse(List<Token> tokens) {
        ListIterator<Token> iterator = tokens.listIterator();
        return parseObject(iterator) && !iterator.hasNext();
    }

    private boolean parseObject(ListIterator<Token> iterator) {
        if (!iterator.hasNext() || iterator.next().type != TokenType.LEFT_BRACE) {
            return false;
        }

        skipWhitespace(iterator);

        if (!iterator.hasNext()) {
            return false;
        }

        Token token = iterator.next();
        if (token.type == TokenType.RIGHT_BRACE) {
            return true;
        }

        while (true) {
            if (token.type != TokenType.STRING) {
                return false;
            }

            skipWhitespace(iterator);

            if (!iterator.hasNext() || iterator.next().type != TokenType.COLON) {
                return false;
            }

            skipWhitespace(iterator);

            if (!iterator.hasNext() || iterator.next().type != TokenType.STRING) {
                return false;
            }

            skipWhitespace(iterator);

            if (!iterator.hasNext()) {
                return false;
            }

            token = iterator.next();
            if (token.type == TokenType.RIGHT_BRACE) {
                return true;
            }

            if (token.type != TokenType.COMMA) {
                return false;
            }

            skipWhitespace(iterator);

            if (!iterator.hasNext()) {
                return false;
            }

            token = iterator.next();
        }
    }

    private void skipWhitespace(ListIterator<Token> iterator) {
        while (iterator.hasNext()) {
            Token token = iterator.next();
            if (token.type != TokenType.WHITESPACE) {
                // Move the iterator back one step
                iterator.previous();
                break;
            }
        }
    }
}