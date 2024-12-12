import lexer.JsonLexer;
import lexer.Token;
import parser.JsonParser;

import java.util.List;

public class JsonParserApplication {
    public static void main(String[] args) {
        JsonLexer lexer = new JsonLexer();
        JsonParser parser = new JsonParser();

        String validJson = "{ \"key\": \"value\", \"key-n\": 101, \"key-o\": {}, \"key-l\": [] }";
        String invalidJson = "{ \"key\": \"value\", \"key-n\": 101, \"key-o\": {, \"key-l\": [] }";

        List<Token> validTokens = lexer.tokenize(validJson);
        List<Token> invalidTokens = lexer.tokenize(invalidJson);

        System.out.println("Testing valid JSON:");
        if (parser.parse(validTokens)) {
            System.out.println("Valid JSON");
        } else {
            System.out.println("Invalid JSON");
        }

        System.out.println("Testing invalid JSON:");
        if (parser.parse(invalidTokens)) {
            System.out.println("Valid JSON");
        } else {
            System.out.println("Invalid JSON");
        }
    }
}