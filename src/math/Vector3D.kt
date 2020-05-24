package math

import java.math.BigDecimal
import java.math.MathContext

@Suppress("MemberVisibilityCanBePrivate")
class Vector3D(x: BigDecimal, y: BigDecimal, z: BigDecimal) {
    val x: BigDecimal = x.setScale(Config.power)
    val y: BigDecimal = y.setScale(Config.power)
    val z: BigDecimal = z.setScale(Config.power)

    constructor(x: Int,y: Int, z: Int): this(BigDecimal(x), BigDecimal(y), BigDecimal(z))

    constructor(x: Long,y: Long, z: Long): this(BigDecimal(x), BigDecimal(y), BigDecimal(z))
    val length: BigDecimal = (x.pow(2) + y.pow(2) + z.pow(2)).sqrt(MathContext.DECIMAL128)

    operator fun plus(other: Vector3D): Vector3D {
        return Vector3D(x + other.x, y + other.y, z + other.z)
    }

    operator fun unaryMinus(): Vector3D {
        return Vector3D(-x, -y, -z)
    }

    operator fun minus(other: Vector3D): Vector3D {
        return Vector3D(x - other.x, y - other.y, z - other.z)
    }

    operator fun times(other: Vector3D): BigDecimal{
        return x * other.x + y * other.y + z * other.z
    }

    operator fun times(other: BigDecimal): Vector3D {
        return Vector3D(x * other, y * other, z * other)
    }

    operator fun div(other: BigDecimal): Vector3D {
        return Vector3D(x / other, y / other, z / other)
    }

    fun normalize(): Vector3D {
        if(length == BigDecimal.ZERO) return ZERO
        return Vector3D(x / length, y / length, z / length)
    }

//    infix fun vectorMul(other: Vector3D): Vector3D {
//
//    }
    companion object {
        val ZERO = Vector3D(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO)
    }
}