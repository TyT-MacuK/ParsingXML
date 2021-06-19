package by.training.parser.validation;

import java.nio.file.Files;
import java.nio.file.Path;

public class FileValidator {

    public static boolean checkFileIsValid(String path) {
        boolean result = false;
        if (path != null) {
            result = Files.exists(Path.of(path));
        }
        return result;
    }
}
