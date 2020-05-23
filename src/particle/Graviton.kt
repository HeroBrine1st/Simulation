package particle

import math.Vector3D
import java.math.BigDecimal

class Graviton(position: Vector3D, private val source: Particle, val target: Particle) : Particle(position, BigDecimal.ZERO) {
    val sourcePos = source.position
    override var velocity: Vector3D
        get() {
            if((position - target.position).length <= Config.c / Config.quantization_coefficient) {
                return (position - target.position).normalize() * (position - target.position).length * Config.quantization_coefficient
            }
            return (target.position - position).normalize() * Config.c
        }
        set(value) = throw RuntimeException("Const value")
    val strength: Vector3D
        get() {
            val direction: Vector3D = sourcePos - position
            return direction * (Config.g * target.mass * source.mass / (target.position - sourcePos).length.pow(2)) /
                    Config.quantization_coefficient // Ибо за одну внутримировую секунды выпускается Config.quantization_coefficient гравитонов
        }

}