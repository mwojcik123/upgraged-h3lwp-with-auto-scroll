package com.homm3.livewallpaper.core

import com.badlogic.gdx.Screen
import com.homm3.livewallpaper.core.layers.H3mLayersGroup
import com.homm3.livewallpaper.core.screens.GameScreen
import com.homm3.livewallpaper.core.screens.LoadingScreen
import com.homm3.livewallpaper.core.screens.SelectAssetsScreen
import com.homm3.livewallpaper.parser.formats.H3m
import kotlinx.coroutines.flow.Flow
import ktx.app.KtxGame

open class Engine(private val prefs: Flow<WallpaperPreferences>) : KtxGame<Screen>(null, false) {
    private lateinit var assets: Assets
    private lateinit var camera: Camera
    open var useScroll = WallpaperPreferences.defaultUseScroll

    open fun onSettingsButtonClick() {}

    private val mapsList: MutableList<H3m> = mutableListOf()

    fun moveCameraByOffset(offset: Float) {
        camera.moveCameraByOffset(offset)
    }
    fun dup(offset: Float) {
        camera.moveCamera()
    }

    override fun create() {
        camera = Camera()
        assets = Assets()
        assets.loadUiAssets()

        addScreen(LoadingScreen(assets) {
            mapsList.forEach {
                getScreen<GameScreen>().addMap(
                    H3mLayersGroup(assets, it)
                )
            }

            setScreen<GameScreen>()
        })
        addScreen(SelectAssetsScreen(assets, ::onSettingsButtonClick))
        addScreen(GameScreen(camera, prefs))

        loadAndStart()
        assets.loadMaps { mapsList.add(it) }
    }

    private fun loadAndStart() {
        setScreen<LoadingScreen>()

        if (assets.isGameAssetsAvailable()) {
            assets.loadGameAssets()
        } else {
            setScreen<SelectAssetsScreen>()
        }
    }

    override fun resume() {
        super.resume()

        if (assets.isGameAssetsLoaded()) {
            setScreen<GameScreen>()
        } else {
            loadAndStart()
        }
    }

    override fun render() {
        assets.manager.update()
        if(useScroll.value == 2){
            camera.moveCamera()
        }
        super.render()
    }
}