from HilbertCurve import hilbert
from ImageImport import importImage, showImage
import numpy as np

slika = importImage()

print("velicina hilberta ", len(slika))
hilbert_curve = hilbert(len(slika))

np.savetxt('hilbert_matrix.txt', hilbert_curve)

result_string = ""
result_numbers = []
for i in range(3):
	for j in range(len(slika) * len(slika)):
		hilbert_index = np.where(hilbert_curve == j)
		red = hilbert_index[0][0]
		stupac = hilbert_index[1][0]
		result_string += chr(slika[red][stupac][i])
		result_numbers.append(slika[red][stupac][i])

np.savetxt('converted_image.txt', result_numbers)

with open('converted_string_image.txt', 'w', encoding='utf-8') as f:
	f.write(result_string)


