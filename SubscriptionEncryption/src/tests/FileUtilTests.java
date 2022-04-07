package tests;

import com.hs1.FileUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileUtilTests {
    private final String testContents = "Test String";
    private final String fileName = "outputTest.txt";

    private static File file;

    @BeforeEach
    void setUp() {
        file = new File(fileName);
        FileUtil.writeToFile(testContents, fileName);
    }

    @Test
    @DisplayName("It writes a file given the name & a string to write")
    public void testWriteToFile() {
        assertTrue(file.exists());
    }

    @Test
    @DisplayName("It gets expected file contents")
    public void testGetFileContents() throws IOException {
        assertEquals(testContents, FileUtil.getFileContents(fileName));
    }

    @AfterEach
    public void deleteFile() {
        file.delete();
    }
}


