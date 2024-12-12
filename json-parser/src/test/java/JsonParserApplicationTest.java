import lexer.JsonLexer;
import lexer.Token;
import org.junit.jupiter.api.Test;
import parser.JsonParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonParserApplicationTest {

    JsonLexer lexer = new JsonLexer();
    JsonParser parser = new JsonParser();

    @Test
    public void testStep1ValidJson() throws IOException {
        String content = new String(Files.readAllBytes(Paths.get("src/main/resources/tests/step1/valid.json")));
        List<Token> tokens = lexer.tokenize(content);
        assertTrue(parser.parse(tokens), "The JSON should be valid");
    }

    @Test
    public void testStep1InvalidJson() throws IOException {
        String content = new String(Files.readAllBytes(Paths.get("src/main/resources/tests/step1/invalid.json")));
        List<Token> tokens = lexer.tokenize(content);
        assertFalse(parser.parse(tokens), "The JSON should be invalid");
    }

    @Test
    public void testStep2ValidJson() throws IOException {
        String content = new String(Files.readAllBytes(Paths.get("src/main/resources/tests/step2/valid.json")));
        List<Token> tokens = lexer.tokenize(content);
        assertTrue(parser.parse(tokens), "The JSON should be valid");
    }

		@Test
		public void testStep2Valid2Json() throws IOException {
			String content = new String(Files.readAllBytes(Paths.get("src/main/resources/tests/step2/valid2.json")));
			List<Token> tokens = lexer.tokenize(content);
			assertTrue(parser.parse(tokens), "The JSON should be valid");
		}

    @Test
    public void testStep2InvalidJson() throws IOException {
        String content = new String(Files.readAllBytes(Paths.get("src/main/resources/tests/step2/invalid.json")));
        List<Token> tokens = lexer.tokenize(content);
        assertFalse(parser.parse(tokens), "The JSON should be invalid");
    }

		@Test
		public void testStep2Invalid2Json() throws IOException {
			String content = new String(Files.readAllBytes(Paths.get("src/main/resources/tests/step2/invalid2.json")));
			List<Token> tokens = lexer.tokenize(content);
			assertFalse(parser.parse(tokens), "The JSON should be invalid");
		}
}