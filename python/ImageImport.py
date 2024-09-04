import cv2

def importImage():
    # Učitavanje slike
    slika = cv2.imread('raw_photo.tiff')

    # Provjera je li slika uspješno učitana
    if slika is not None:
        # Prikaz dimenzija slike
        visina, širina, _ = slika.shape
        print("Dimenzije slike:", širina, "x", visina)
        
        izrezana_slika = slika[0:512, 512:1024, :]
        cv2.imwrite("pocetna.tiff", izrezana_slika)

        visina2, širina2, _ = izrezana_slika.shape
        print("Dimenzije izrezane slike:", širina2, "x", visina2)

        return izrezana_slika

def showImage(slika):
    # Pretvaranje trodimenzionalne matrice u sliku koristeći cv2
    slika = cv2.cvtColor(slika, cv2.COLOR_RGB2BGR)

    # Prikazivanje slike
    cv2.imwrite("original.tiff", slika)
    #cv2.imshow('Slika', slika)
    #cv2.waitKey(0)
    #cv2.destroyAllWindows()