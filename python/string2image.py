import numpy as np
from ImageImport import importImage, showImage

## VRACANJE U ORIGINAL

loaded_hilbert = np.loadtxt('hilbert_matrix.txt')
#loaded_result_numbers = np.loadtxt('converted_image.txt')
loaded_result_numbers_string = []

with open('BWTDecodedFile.txt', 'r', encoding='utf-8') as f:
	content = f.read()
	for char in content:
		loaded_result_numbers_string.append(ord(char))

slika = importImage()
#originalna_slika = np.zeros((len(slika), len(slika), 3))
originalna_slika_string = np.zeros((len(slika), len(slika), 3))

dimenzija = 2
br = 0

for i in range(3*len(slika) * len(slika)):

	if(br == len(slika)*len(slika)):
		dimenzija -= 1
		br = 0
	
	hilbert_index = np.where(loaded_hilbert == br)
	red = hilbert_index[0][0]
	stupac = hilbert_index[1][0]
	#originalna_slika[red][stupac][dimenzija] = loaded_result_numbers[i]
	originalna_slika_string[red][stupac][dimenzija] = loaded_result_numbers_string[i]

	br += 1

#originalna_slika = originalna_slika.astype(np.uint8)
originalna_slika_string = originalna_slika_string.astype(np.uint8)

#showImage(originalna_slika)
showImage(originalna_slika_string)