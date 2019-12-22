import math

PI = math.pi  # Применяется в расчетах
LIGHT_SPEED = 1  # Собственная единица. Собственная единица и ограничение точности
# позволяет повысить точность засчет допущения телепортации частиц.
GRAVITATIONAL_CONSTANT = 1
PLANCK_CONSTANT = 1

G = GRAVITATIONAL_CONSTANT
c = LIGHT_SPEED
h = PLANCK_CONSTANT

######################

BORDER_RADIUS = 2 ** 64  # Радиус ограничения. Если частица покинет этот радиус, окажется на другой стороне симуляции в той же точке

######################
# Преобразования внутренних величин во внешние (Если пользователь захочет посмотреть текущее время или положение частицы)

INTERNAL_METRICS_TO_REAL = (10 ** 36)/16
INTERNAL_TIME_TO_REAL = (10 ** 45)/53

######################


