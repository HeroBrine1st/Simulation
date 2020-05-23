package particle

import math.Vector3D
import java.math.BigDecimal

abstract class Particle(val position: Vector3D = Vector3D.ZERO, val mass: BigDecimal = BigDecimal.ZERO) {
    var velocity = Vector3D.ZERO
}