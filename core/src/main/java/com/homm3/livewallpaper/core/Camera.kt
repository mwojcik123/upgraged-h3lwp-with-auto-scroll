package com.homm3.livewallpaper.core

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.Vector2
import kotlin.random.Random
import kotlin.math.sqrt


class Camera : OrthographicCamera() {
    private val cameraPoint = Vector2()
    private val nextCameraPoint = Vector2()
    private var mapka = 0;
    private var posx: Double = 0.0;
    private var posy: Double = 0.0;

    private var sin: Double = 0.0;
    private var cos: Double = 0.0;


    init {
        setToOrtho(true)

    }

    fun randomizeCameraPosition(mapSize: Int) {
        this.mapka = mapSize
        val mapSizeFloat = mapSize * Constants.TILE_SIZE

        val halfWidth = viewportWidth / 2
        val xStart = halfWidth
        val xEnd = mapSizeFloat - xStart - halfWidth
        val nextCameraX = xStart + Random.nextFloat() * xEnd
        val nnextCameraX = xStart + Random.nextFloat() * xEnd

        val halfHeight = viewportHeight / 2
        val yStart = halfHeight
        val yEnd = mapSizeFloat - yStart - halfHeight
        val nextCameraY = yStart + Random.nextFloat() * yEnd
        val nnextCameraY = yStart + Random.nextFloat() * yEnd

        this.posx = nextCameraX.toDouble()
        this.posy = nextCameraY.toDouble()
        cameraPoint.set(nextCameraX, nextCameraY)
        nextCameraPoint.set(nnextCameraX,nnextCameraY)
        position.set(cameraPoint, 0f)

        val a1 = sqrt(Math.pow((cameraPoint.x - cameraPoint.y).toDouble(), 2.0) + Math.pow((cameraPoint.x - nnextCameraX).toDouble(), 2.0))
        val b2= sqrt(Math.pow((cameraPoint.y - cameraPoint.x).toDouble(), 2.0) + Math.pow((nnextCameraY - cameraPoint.y).toDouble(), 2.0))
        val c2= sqrt(Math.pow((cameraPoint.x - nnextCameraX).toDouble(), 2.0) + Math.pow((cameraPoint.y - nnextCameraY).toDouble(), 2.0))
        this.sin = a1/c2 ;
        this.cos =  b2/c2;
        update()
    }

    fun moveCameraByOffset(offset: Float) {
        position.x = cameraPoint.x + offset * Constants.SCROLL_OFFSET


    }
    fun moveCamera() {

//        val a1 = sqrt(Math.pow((cameraPoint.x - cameraPoint.y).toDouble(), 2.0) + Math.pow((cameraPoint.x - nextCameraPoint.x).toDouble(), 2.0))
//        val b2= sqrt(Math.pow((cameraPoint.y - cameraPoint.x).toDouble(), 2.0) + Math.pow((nextCameraPoint.y - cameraPoint.y).toDouble(), 2.0))
//        val c2= sqrt(Math.pow((cameraPoint.x - nextCameraPoint.x).toDouble(), 2.0) + Math.pow((cameraPoint.y - nextCameraPoint.y).toDouble(), 2.0))
//        var sin = a1/c2 ;
//        var cos =  b2/c2;
        var movex: Double = 0.0;
        var movey: Double  = 0.0;

        if(cameraPoint.x - nextCameraPoint.x < 30f && cameraPoint.y - nextCameraPoint.y < 30f){
            val mapSize = this.mapka
            val mapSizeFloat = mapSize * Constants.TILE_SIZE
            val halfWidth = viewportWidth / 2
            val xStart = halfWidth
            val xEnd = mapSizeFloat - xStart - halfWidth

            val nnextCameraX = xStart + Random.nextFloat() * xEnd
            val halfHeight = viewportHeight / 2
            val yStart = halfHeight
            val yEnd = mapSizeFloat - yStart - halfHeight

            val nnextCameraY = yStart + Random.nextFloat() * yEnd

            nextCameraPoint.set(nnextCameraX, nnextCameraY)

            val a1 = sqrt(Math.pow((cameraPoint.x - cameraPoint.y).toDouble(), 2.0) + Math.pow((cameraPoint.x - nnextCameraX).toDouble(), 2.0))
            val b2= sqrt(Math.pow((cameraPoint.y - cameraPoint.x).toDouble(), 2.0) + Math.pow((nnextCameraY - cameraPoint.y).toDouble(), 2.0))
            val c2= sqrt(Math.pow((cameraPoint.x - nnextCameraX).toDouble(), 2.0) + Math.pow((cameraPoint.y - nnextCameraY).toDouble(), 2.0))
            this.sin = a1/c2 ;
            this.cos =  b2/c2;

            update()
        }
        if(cameraPoint.x < nextCameraPoint.x && cameraPoint.x - nextCameraPoint.x <= 3f){

            movex = if(this.sin < 1) {
                2.0 * this.sin
            }else{
                2.0
            }
        }
        if(cameraPoint.y < nextCameraPoint.y && cameraPoint.y - nextCameraPoint.y <= 3f){
            movey = if(this.cos < 1) {
                2.0  * this.cos
            }else{
                2.0
            }
        }
        if(cameraPoint.x > nextCameraPoint.x && cameraPoint.x - nextCameraPoint.x >= 3f){
            movex = if(this.sin < 1) {
                -2.0 * this.sin
            }else{
                -2.0
            }
        }
        if(cameraPoint.y > nextCameraPoint.y && cameraPoint.y - nextCameraPoint.y >= 3f){
            movey = if(this.cos < 1) {
                - 2.0 * this.cos
            }else{
                -2.0
            }
        }
        this.posx = this.posx + movex
        this.posy = this.posy + movey
        cameraPoint.set(this.posx.toFloat(), this.posy.toFloat())
        position.set(cameraPoint, 0f)
        update()

    }
}