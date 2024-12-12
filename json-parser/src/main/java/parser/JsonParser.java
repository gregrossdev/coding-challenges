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

        iterator.previous();

        while (true) {
            skipWhitespace(iterator);

            if (!iterator.hasNext()) {
                return false;
            }

            token = iterator.next();
            if (token.type != TokenType.STRING) {
                return false;
            }

            skipWhitespace(iterator);

            if (!iterator.hasNext() || iterator.next().type != TokenType.COLON) {
                return false;
            }

            skipWhitespace(iterator);

            if (!iterator.hasNext()) {
                return false;
            }

            token = iterator.next();
            if (!parseValue(token, iterator)) {
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
        }
    }

    private boolean parseArray(ListIterator<Token> iterator) {
        if (!iterator.hasNext() || iterator.next().type != TokenType.LEFT_BRACKET) {
            return false;
        }

        skipWhitespace(iterator);

        if (!iterator.hasNext()) {
            return false;
        }

        Token token = iterator.next();
        if (token.type == TokenType.RIGHT_BRACKET) {
            return true;
        }

        iterator.previous();

        while (true) {
            skipWhitespace(iterator);

            if (!iterator.hasNext()) {
                return false;
            }

            token = iterator.next();
            if (!parseValue(token, iterator)) {
                return false;
            }

            skipWhitespace(iterator);

            if (!iterator.hasNext()) {
                return false;
            }

            token = iterator.next();
            if (token.type == TokenType.RIGHT_BRACKET) {
                return true;
            }

            if (token.type != TokenType.COMMA) {
                return false;
            }
        }
    }

    private boolean parseValue(Token token, ListIterator<Token> iterator) {
        switch (token.type) {
            case STRING:
            case NUMBER:
            case BOOLEAN:
            case NULL:
                return true;
            case LEFT_BRACE:
                iterator.previous();
                return parseObject(iterator);
            case LEFT_BRACKET:
                iterator.previous();
                return parseArray(iterator);
            default:
                return false;
        }
    }

    private void skipWhitespace(ListIterator<Token> iterator) {
        while (iterator.hasNext()) {
            Token token = iterator.next();
            if (token.type != TokenType.WHITESPACE) {
                iterator.previous();
                break;
            }
        }
    }
}