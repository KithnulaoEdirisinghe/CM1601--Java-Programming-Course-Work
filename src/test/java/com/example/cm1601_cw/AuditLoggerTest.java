package com.example.cm1601_cw;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AuditLoggerTest {

    @Test
    void appendsFormattedLineToAuditLog() throws IOException {
        Path logPath = Path.of("audit_log.txt");
        long linesBefore = Files.exists(logPath) ? Files.lines(logPath).count() : 0;

        AuditLogger.log("TEST_ACTION", "P999", 7);

        List<String> lines = Files.readAllLines(logPath);
        String lastLine = lines.get(lines.size() - 1);

        assertEquals(linesBefore + 1, lines.size());
        assertTrue(lastLine.contains("TEST_ACTION"));
        assertTrue(lastLine.contains("P999"));
        assertTrue(lastLine.contains("7"));
        assertTrue(lastLine.matches("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} \\|.*"));
    }

    @Test
    void doesNotOverwritePreviousEntries() throws IOException {
        AuditLogger.log("FIRST_TEST", "P900", 1);
        long linesAfterFirst = Files.lines(Path.of("audit_log.txt")).count();

        AuditLogger.log("SECOND_TEST", "P901", 2);
        long linesAfterSecond = Files.lines(Path.of("audit_log.txt")).count();

        assertEquals(linesAfterFirst + 1, linesAfterSecond);
    }
}
