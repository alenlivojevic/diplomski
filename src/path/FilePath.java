package path;

public enum FilePath {
    SLIKA_POCETNA("C:\\Projects\\KIK_projekt_2\\slika.jpg"),
    SLIKA_VRACENA("C:\\Projects\\KIK_projekt_2\\original.jpg"),
    BASE64("C:\\Projects\\KIK_projekt_2\\base64.txt"),
    BWTEncoded("C:\\Projects\\KIK_projekt_2\\BWTEncodedFile.txt"),
    RLEEncoded("C:\\Projects\\KIK_projekt_2\\RLEEncodedFile.txt"),
    LZWEncoded("C:\\Projects\\KIK_projekt_2\\LZWEncodedFile.txt"),
    RLEDecoded("C:\\Projects\\KIK_projekt_2\\RLEDecodedFile.txt"),
    BWTDecoded("C:\\Projects\\KIK_projekt_2\\BWTDecodedFile.txt");



    private final String path;
    FilePath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
