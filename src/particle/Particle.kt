package particle

import math.Vector3D
import java.math.BigDecimal

abstract class Particle(var position: Vector3D, val mass: BigDecimal) {
    open var velocity = Vector3D.ZERO

    open fun process() {
        position += velocity / Config.quantization_coefficient
    }
}