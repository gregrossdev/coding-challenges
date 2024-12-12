import lexer.JsonLexer;
import lexer.Token;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import parser.JsonParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class JsonParserApplicationTest {

    JsonLexer lexer = new JsonLexer();
    JsonParser parser = new JsonParser();

    @Test
    @Order(1)
    public void testStep1ValidJson() throws IOException {
        String content = new String(Files.readAllBytes(Paths.get("src/main/resources/tests/step1/valid.json")));
        List<Token> tokens = lexer.tokenize(content);
        assertTrue(parser.parse(tokens), "The JSON should be valid");
    }

    @Test
    @Order(2)
    public void testStep1InvalidJson() throws IOException {
        String content = new String(Files.readAllBytes(Paths.get("src/main/resources/tests/step1/invalid.json")));
        List<Token> tokens = lexer.tokenize(content);
        assertFalse(parser.parse(tokens), "The JSON should be invalid");
    }

    @Test
    @Order(3)
    public void testStep2ValidJson() throws IOException {
        String content = new String(Files.readAllBytes(Paths.get("src/main/resources/tests/step2/valid.json")));
        List<Token> tokens = lexer.tokenize(content);
        assertTrue(parser.parse(tokens), "The JSON should be valid");
    }

    @Test
    @Order(4)
    public void testStep2Valid2Json() throws IOException {
        String content = new String(Files.readAllBytes(Paths.get("src/main/resources/tests/step2/valid2.json")));
        List<Token> tokens = lexer.tokenize(content);
        assertTrue(parser.parse(tokens), "The JSON should be valid");
    }

    @Test
    @Order(5)
    public void testStep2InvalidJson() throws IOException {
        String content = new String(Files.readAllBytes(Paths.get("src/main/resources/tests/step2/invalid.json")));
        List<Token> tokens = lexer.tokenize(content);
        assertFalse(parser.parse(tokens), "The JSON should be invalid");
    }

    @Test
    @Order(6)
    public void testStep2Invalid2Json() throws IOException {
        String content = new String(Files.readAllBytes(Paths.get("src/main/resources/tests/step2/invalid2.json")));
        List<Token> tokens = lexer.tokenize(content);
        assertFalse(parser.parse(tokens), "The JSON should be invalid");
    }

    @Test
    @Order(7)
    public void testStep3ValidJson() throws IOException {
        String content = new String(Files.readAllBytes(Paths.get("src/main/resources/tests/step3/valid.json")));
        List<Token> tokens = lexer.tokenize(content);
        assertTrue(parser.parse(tokens), "The JSON should be valid");
    }

    @Test
    @Order(8)
    public void testStep3InvalidJson() throws IOException {
        String content = new String(Files.readAllBytes(Paths.get("src/main/resources/tests/step3/invalid.json")));
        List<Token> tokens = lexer.tokenize(content);
        assertFalse(parser.parse(tokens), "The JSON should be invalid");
    }

    @Test
    @Order(9)
    public void testStep4ValidJson() throws IOException {
        String content = new String(Files.readAllBytes(Paths.get("src/main/resources/tests/step4/valid.json")));
        List<Token> tokens = lexer.tokenize(content);
        assertTrue(parser.parse(tokens), "The JSON should be valid");
    }

    @Test
    @Order(10)
    public void testStep4Valid2Json() throws IOException {
        String content = new String(Files.readAllBytes(Paths.get("src/main/resources/tests/step4/valid2.json")));
        List<Token> tokens = lexer.tokenize(content);
        assertTrue(parser.parse(tokens), "The JSON should be valid");
    }

    @Test
    @Order(11)
    public void testStep4InvalidJson() throws IOException {
        String content = new String(Files.readAllBytes(Paths.get("src/main/resources/tests/step4/invalid.json")));
        List<Token> tokens = lexer.tokenize(content);
        assertFalse(parser.parse(tokens), "The JSON should be invalid");
    }
}