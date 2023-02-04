package com.homm3.livewallpaper.core

enum class MapUpdateInterval(val value: Int) {
    EVERY_SWITCH(0),
    MINUTES_10(1),
    MINUTES_30(2),
    HOURS_2(3),
    HOURS_24(4);

    companion object {
        fun fromInt(value: Int?): MapUpdateInterval {
            return values()
                .runCatching { first { it.value == value } }
                .getOrDefault(WallpaperPreferences.defaultMapUpdateInterval)
        }
    }
}
enum class UseScroll(val value: Int) {
    NO_SCROLL(0),
    OFF_SET_SCROLL(1),
    AUTO_SCROLLING(2);

    companion object {
        fun fromInt(value: Int?): UseScroll {
            return values()
                .runCatching { first { it.value == value } }
                .getOrDefault(WallpaperPreferences.defaultUseScroll)
        }
    }
}
enum class Scale(val value: Int) {
    DPI(0),
    X1(1),
    X2(2),
    X3(3),
    X4(4);

    companion object {
        fun fromInt(value: Int?): Scale {
            return values()
                .runCatching { first { it.value == value } }
                .getOrDefault(WallpaperPreferences.defaultScale)
        }
    }
}

data class WallpaperPreferences(
    val scale: Scale = defaultScale,
    val mapUpdateInterval: MapUpdateInterval = defaultMapUpdateInterval,
    val useScroll: UseScroll = defaultUseScroll,
    val brightness: Float = defaultBrightness,
) {
    companion object Defaults {
        val defaultScale = Scale.DPI
        val defaultMapUpdateInterval = MapUpdateInterval.MINUTES_10
        val defaultUseScroll = UseScroll.AUTO_SCROLLING
        const val defaultBrightness = 0.6f
    }
}
