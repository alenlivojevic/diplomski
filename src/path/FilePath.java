package path;

public enum FilePath {
    SLIKA_POCETNA("C:\\Projects\\DIPLOMSKI_GIT\\diplomski\\slika.jpg"),
    SLIKA_VRACENA("C:\\Projects\\DIPLOMSKI_GIT\\diplomski\\original.jpg"),
    BASE64("C:\\Projects\\DIPLOMSKI_GIT\\diplomski\\base64.txt"),
    HILBERT("C:\\Projects\\DIPLOMSKI_GIT\\diplomski\\converted_string_image.txt"),
    BWTEncoded("C:\\Projects\\DIPLOMSKI_GIT\\diplomski\\BWTEncodedFile.txt"),
    RLEEncoded("C:\\Projects\\DIPLOMSKI_GIT\\diplomski\\RLEEncodedFile.txt"),
    LZWEncoded("C:\\Projects\\DIPLOMSKI_GIT\\diplomski\\LZWEncodedFile.txt"),
    LZWDecoded("C:\\Projects\\DIPLOMSKI_GIT\\diplomski\\LZWDecodedFile.txt"),
    RLEDecoded("C:\\Projects\\DIPLOMSKI_GIT\\diplomski\\RLEDecodedFile.txt"),
    BWTDecoded("C:\\Projects\\DIPLOMSKI_GIT\\diplomski\\BWTDecodedFile.txt");



    private final String path;
    FilePath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
