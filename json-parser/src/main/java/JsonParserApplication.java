import java.util.List;

public class JsonParserApplication {
    public static void main(String[] args) {
        JsonLexer lexer = new JsonLexer();
        JsonParser parser = new JsonParser();

        String validJson = "{}";
        String invalidJson = "{";

        List<JsonLexer.Token> validTokens = lexer.tokenize(validJson);
        List<JsonLexer.Token> invalidTokens = lexer.tokenize(invalidJson);

//        if (parser.parse(validTokens)) {
//            System.out.println("Valid JSON");
//            System.exit(0);
//        } else {
//            System.out.println("Invalid JSON");
//            System.exit(1);
//        }

        if (parser.parse(invalidTokens)) {
            System.out.println("Valid JSON");
            System.exit(0);
        } else {
            System.out.println("Invalid JSON");
            System.exit(1);
        }
    }
}