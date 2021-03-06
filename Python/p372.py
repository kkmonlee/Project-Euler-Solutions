from decimal import *

# Evaluates number of points (x, y) in a right-angled triangle ax + by <= c
# Where x > 0 and y > 0
def getNumberOfPoints(a, b, c):
    m = c / a
    if a == b:
        return (m * (m - 1)) / 2
    k, h = (a - 1) / b, (c - a * m) / b

    return getNumberOfPoints(b, a - b * k, c - b * (k * m + h)) + k * (m * (m - 1)) / 2 + m * h

# Evaluates number of lattice points (x, y) in [M + 1, N]^2 such that k <= (y * y)/(x * x)
def getLatticePoints(k, M, N):
    s, q = Decimal(k).sqrt(), 10**30
    p = int(s * q)
    if N * q - M * p <= 0: return 0

    return getNumberOfPoints(p, q, N * q - M * p) + (N * q) / p - M

def getRay(k, M, N):
    return getLatticePoints(2 * k + 1, M, N) - getLatticePoints(2 * k + 2, M, N)

M, N = 2*10**6, 10**9
print sum([getRay(k, M, N) for k in xrange(0, (N * N) / (M * M) + 1)])