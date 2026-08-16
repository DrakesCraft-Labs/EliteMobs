package com.magmaguy.elitemobs.integrations.drakes;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/**
 * Decide que sistemas propios de EliteMobs se apagan al arrancar dentro de DrakesCraft.
 *
 * EliteMobs no es solo un motor de mobs: trae ademas su propia economia, sus misiones, sus
 * tiendas, su gremio de aventureros y su progresion de jugador. DrakesCraft ya tiene todo eso
 * resuelto --Dragmas y Vault para la economia, Quests para las misiones, la tienda de Odysseia y
 * Tebex para las compras, LuckPerms para los rangos-- asi que dejarlos activos significaria dos
 * economias, dos monedas y dos progresiones compitiendo por el mismo jugador.
 *
 * De EliteMobs interesa una sola cosa: su motor de mobs, con la IA, los poderes y las plantillas.
 * Todo lo demas se apaga aqui en vez de borrarse del codigo, por dos razones: el fork sigue
 * pudiendo traerse los arreglos de upstream sin conflictos interminables, y cualquier decision es
 * reversible desde un yml si algun dia interesa activar una pieza concreta.
 */
public final class DrakesStandaloneModules {

    private static boolean soloMotorDeMobs = true;
    private static boolean cargado = false;

    private DrakesStandaloneModules() {
    }

    /** Lee la preferencia del mismo archivo que configura el puente. */
    public static void load(JavaPlugin plugin) {
        File archivo = new File(plugin.getDataFolder(), "drakes-integration.yml");
        if (!archivo.exists()) {
            plugin.saveResource("drakes-integration.yml", false);
        }
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(archivo);
        soloMotorDeMobs = yaml.getBoolean("solo-motor-de-mobs", true);
        cargado = true;
    }

    /**
     * True cuando EliteMobs debe comportarse como un motor de mobs y nada mas.
     *
     * Por defecto es true incluso sin configuracion leida: si algo fallara al cargar el yml,
     * el lado seguro es no encender una segunda economia en un servidor que ya tiene la suya.
     */
    public static boolean soloMotorDeMobs() {
        return !cargado || soloMotorDeMobs;
    }

    /** Economia, tiendas y apuestas propias: DrakesCraft usa Dragmas, Odysseia y Tebex. */
    public static boolean economiaPropiaActiva() {
        return !soloMotorDeMobs();
    }

    /** Misiones y dialogos propios: el servidor ya corre Quests. */
    public static boolean misionesPropiasActivas() {
        return !soloMotorDeMobs();
    }

    /** Gremio de aventureros y progresion de rangos: eso lo lleva LuckPerms. */
    public static boolean progresionPropiaActiva() {
        return !soloMotorDeMobs();
    }
}
