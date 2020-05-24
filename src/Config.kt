import ninja.leaping.configurate.commented.CommentedConfigurationNode
import ninja.leaping.configurate.hocon.HoconConfigurationLoader
import java.math.BigDecimal
import java.nio.file.Paths
import kotlin.math.pow

object Config {
    val c: BigDecimal = BigDecimal(299792458)
        get() = BigDecimal(getValue(field.toInt(), "light_speed", comment = "Скорость света"))
    val power = 60
        get() = getValue(field, "quantization", "power", comment = "Показатель модификатора квантования. Положителен")
    val quantization_coefficient: BigDecimal
        get() = BigDecimal(10.toDouble().pow(power)).setScale(power * 2)
    private val g_base = 10
        get() = getValue(field, "gravitational_constant", "base", comment = "Основание")
    private val g_power = -11
        get() = getValue(field, "gravitational_constant", "power", comment = "Показатель")
    private val g_significand = 6.6743015
        get() = getValue(field, "gravitational_constant", "significand", comment = "Мантисса")
    val g: BigDecimal by lazy { BigDecimal(g_significand) * BigDecimal(g_base.toDouble().pow(g_power)) }

    private val nodeLoader: HoconConfigurationLoader = HoconConfigurationLoader.builder()
        .setPath(Paths.get("config.conf"))
        .build()
    private val rootNode: CommentedConfigurationNode = nodeLoader.load()

    private inline fun <reified T> getValue(defaultValue: T, vararg path: String, comment: String? = null): T {
        val node = rootNode.getNode(*path)
        if (node.isVirtual) {
            node.value = defaultValue
            if (comment != null) {
                node.setComment(comment)
            }
            nodeLoader.save(rootNode)
        }

        return if (node.value is T) {
            node.value as T
        } else {
            System.err.println("Node ${path.joinToString(".")} have invalid type")
            println("Processing node ${path.joinToString(".")} with default value $defaultValue")
            defaultValue
        }
    }
}