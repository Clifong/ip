package Acheron.Exceptions;

public class CorruptedFileException extends Exception {
    @Override
    public String toString() {
        return "Corrupted file. Cannot read data";
    }
}
