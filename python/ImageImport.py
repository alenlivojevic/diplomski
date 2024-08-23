import cv2

def importImage():
    # Učitavanje slike
    slika = cv2.imread('slika.jpg')

    # Provjera je li slika uspješno učitana
    if slika is not None:
        # Prikaz dimenzija slike
        visina, širina, _ = slika.shape
        print("Dimenzije slike:", širina, "x", visina)
        
        izrezana_slika = slika[400:528, 400:528, :]

        visina2, širina2, _ = izrezana_slika.shape
        print("Dimenzije izrezane slike:", širina2, "x", visina2)

        return izrezana_slika

def showImage(slika):
    # Pretvaranje trodimenzionalne matrice u sliku koristeći cv2
    slika = cv2.cvtColor(slika, cv2.COLOR_RGB2BGR)

    # Prikazivanje slike
    cv2.imwrite("original.jpg", slika)
    cv2.imshow('Slika', slika)
    cv2.waitKey(0)
    cv2.destroyAllWindows()