import math.Vector3D
import particle.Graviton
import particle.Particle
import particle.RealParticle
import java.math.BigDecimal
import java.math.BigInteger
import java.math.RoundingMode

val particles: ArrayList<Particle> = ArrayList()
var time: BigInteger = BigInteger.ZERO // Собственная единица
val size: BigDecimal = Config.quantization_coefficient
//    get() {
//        return BigDecimal.ONE / Config.quantization_coefficient * time.toBigDecimal().sqrt(MathContext.DECIMAL32).setScale(0, RoundingMode.FLOOR)
//    }

fun main() {
    val particle1 = RealParticle(Vector3D(200, 500, 100), BigDecimal.ONE)
    val particle2 = RealParticle(Vector3D(700, 1245, -122), BigDecimal(10000000000000000))
    particle1.velocity += Vector3D(5, 0, 12)
    particle2.velocity += Vector3D(2, -5, -7)
    particles.add(particle1)
    particles.add(particle2)

    while(true) {
        time += BigInteger.ONE
        println("Age: ${time.toBigDecimal().divide(Config.quantization_coefficient, Config.power, RoundingMode.HALF_UP)}s ($time internal)")
        println("Distance: ${(particle1.position - particle2.position).length}")
        particles.toTypedArray().forEach {
            it.process()
            if(it is RealParticle) {
                println("${it.javaClass.name} ${it.position.x} ${it.position.y} ${it.position.z} with velocity ${it.velocity.x} ${it.velocity.y} ${it.velocity.z} ")
                particles.toTypedArray().forEach { other ->
                    if(other !is Graviton && it != other && it.position - other.position != Vector3D.ZERO ) {
                        particles.add(Graviton(it.position, it, other))
                    }
                }
            }else if(it is Graviton) {
                if((it.target.position - it.position).length <= BigDecimal.TEN / Config.quantization_coefficient) {
                    it.target.velocity += it.strength
                    println("Touch")
                    particles.remove(it)
                }
            }
        }
    }
}