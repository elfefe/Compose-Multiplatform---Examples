package com.elfefe.rpgtest.utils

import com.elfefe.rpgtest.utils.simplexnoise.OpenSimplex2S
import com.elfefe.rpgtest.utils.simplexnoise.OpenSimplexNoise
import com.elfefe.rpgtest.utils.simplexnoise.SimplexNoise
import kotlin.math.floor


/***
 * Generate an array noise between 0 and 1
 *
 * @param width The array width
 * @param height The array height
 *
 * *************** @author Donelfefe *******************/
fun generate(width: Int, height: Int): Array<Array<Float>> {
    val array = Array(width) {Array(height) {0f} }
    for (i in 0 until width * height) {
        val y = floor(i / height.toFloat()).toInt()
        val x = i - (y * height)
//        val noise = SimplexNoise.noise(x / 150.0, y / 150.0).toFloat()
//        val noise = com.elfefe.rpgtest.utils.simplexnoise.fromc.SimplexNoise.noise(x / 100f, y / 100f)
//        val noise = OpenSimplexNoise(1).eval(x / 100f, y / 100f)
//        val noise = ImprovedNoise.noise(x / 2.0, y / 2.0, 1.0)
//        array[x][y] = noise.toFloat()
    }
    return array
}