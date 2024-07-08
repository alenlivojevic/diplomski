import numpy as np

def hilbert(X):
    if isinstance(X, int):
        x = np.ceil(np.log2(X))
        Y = np.array([[1, 2], [0, 3]])
        for _ in range(int(x) - 1):
            Y = hilbert(Y)
        return Y
    elif isinstance(X, np.ndarray):
        M, N = X.shape
        if M != 2 and N != 2:
            Y = np.block([[hilbert(X[:M//2, :N//2]), hilbert(X[:M//2, N//2:])],
                          [hilbert(X[M//2:, :N//2]), hilbert(X[M//2:, N//2:])]])
        else:
            offset = np.min(X)
            X = X - offset
            A = np.array([[1, 2], [0, 3]])
            B = np.array([[5, 6, 9, 10], [4, 7, 8, 11], [3, 2, 13, 12], [0, 1, 14, 15]])
            C = np.rot90(A)
            D = np.rot90(B)
            if np.array_equal(X, A):
                Y = B
            elif np.array_equal(X, np.fliplr(A)):
                Y = np.fliplr(B)
            elif np.array_equal(X, np.flipud(A)):
                Y = np.flipud(B)
            elif np.array_equal(X, np.fliplr(np.flipud(A))):
                Y = np.fliplr(np.flipud(B))
            elif np.array_equal(X, C):
                Y = D
            elif np.array_equal(X, np.fliplr(C)):
                Y = np.fliplr(D)
            elif np.array_equal(X, np.flipud(C)):
                Y = np.flipud(D)
            elif np.array_equal(X, np.fliplr(np.flipud(C))):
                Y = np.fliplr(np.flipud(D))
            Y = Y + offset * 4
        return Y

