package com.elikson.asteroidesspace

import android.os.Bundle
import com.elikson.superbasic2d.GameActivity
import com.elikson.superbasic2d.ObjectGame
import kotlin.random.Random

class MainActivity : GameActivity() {

    var pontos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        objectGames.add(ObjectGame(this, R.drawable.ic_background, 0, 0, 480, 800, "ic_bg"))
        objectGames.add(ObjectGame(this, R.drawable.ic_background, 0, -800, 480, 800, "ic_bg2"))
        objectGames.add(ObjectGame(this, intArrayOf(R.drawable.ic_nave_one, R.drawable.ic_nave_two, R.drawable.ic_nave_three), 215, 690, 70, 90, "ic_nave"))
        objectGames.add(ObjectGame(this, R.drawable.ic_bullet, 240, 680, 20, 16, "ic_bullet1"))
        objectGames.add(ObjectGame(this, R.drawable.ic_bullet, 240, 380, 20, 16, "ic_bullet2"))
        objectGames.add(ObjectGame(this, R.drawable.ic_asteroide_one, 60, -100, 100, 100, "ic_asteroide1"))
        objectGames.add(ObjectGame(this, R.drawable.ic_asteroide_two, 180, -400, 100, 100, "ic_asteroide2"))
        objectGames.add(ObjectGame(this, R.drawable.ic_asteroide_three, 320, -700, 100, 100, "ic_asteroide3"))
        objectGames.add(ObjectGame(R.string.impact, 100, 30, 1f, "Pontos: 0", "text_score"))

        setContentView(getSurfaceView())
    }

    fun posicaoAsteroide(): Int {
        var posicao = 60
        val opcao = Random.nextInt(1,4)
        if(opcao == 1){
            posicao = 60
        } else if(opcao == 2){
            posicao = 180
        } else {
            posicao = 320
        }
        return posicao
    }

    fun somarPontos() {
        pontos++
        getObject("text_score")?.text = "Pontos: " + pontos
    }

    fun zerarPontos() {
        pontos = 0
        getObject("text_score")?.text = "Pontos: " + pontos
    }

    override fun drawing() {
        super.drawing()

        if(isTouch(getObject("ic_bullet1")?.x?.toFloat()!!, getObject("ic_bullet1")?.y?.toFloat()!!, "ic_asteroide1")){
            getObject("ic_asteroide1")?.isVisibility = false
            somarPontos()
        }

        if(isTouch(getObject("ic_bullet1")?.x?.toFloat()!!, getObject("ic_bullet1")?.y?.toFloat()!!, "ic_asteroide2")){
            getObject("ic_asteroide2")?.isVisibility = false
            somarPontos()
        }

        if(isTouch(getObject("ic_bullet1")?.x?.toFloat()!!, getObject("ic_bullet1")?.y?.toFloat()!!, "ic_asteroide3")){
            getObject("ic_asteroide3")?.isVisibility = false
            somarPontos()
        }

        if(isTouch(getObject("ic_nave")?.x?.toFloat()!!, getObject("ic_nave")?.y?.toFloat()!!, "ic_asteroide1")){
            zerarPontos()
        }

        if(isTouch(getObject("ic_nave")?.x?.toFloat()!!, getObject("ic_nave")?.y?.toFloat()!!, "ic_asteroide2")){
            zerarPontos()
        }

        if(isTouch(getObject("ic_nave")?.x?.toFloat()!!, getObject("ic_nave")?.y?.toFloat()!!, "ic_asteroide3")){
            zerarPontos()
        }

        getObject("ic_asteroide1")?.moveByY(5)
        getObject("ic_asteroide2")?.moveByY(5)
        getObject("ic_asteroide3")?.moveByY(5)

        if(getObject("ic_asteroide1")?.y!! >= 800){
            getObject("ic_asteroide1")?.y = -100
            getObject("ic_asteroide1")?.x = posicaoAsteroide()
            getObject("ic_asteroide1")?.isVisibility = true
        }

        if(getObject("ic_asteroide2")?.y!! >= 800){
            getObject("ic_asteroide2")?.y = -100
            getObject("ic_asteroide2")?.x = posicaoAsteroide()
            getObject("ic_asteroide2")?.isVisibility = true
        }

        if(getObject("ic_asteroide3")?.y!! >= 800){
            getObject("ic_asteroide3")?.y = -100
            getObject("ic_asteroide3")?.x = posicaoAsteroide()
            getObject("ic_asteroide3")?.isVisibility = true
        }


        getObject("ic_bullet1")?.moveByY(-5)
        getObject("ic_bullet2")?.moveByY(-5)

        if(getObject("ic_bullet1")?.y!! < 0){
            getObject("ic_bullet1")?.y = getObject("ic_nave")?.y!! - 10
            getObject("ic_bullet1")?.x = getObject("ic_nave")?.x!! + 25
        }

        if(getObject("ic_bullet2")?.y!! < 0){
            getObject("ic_bullet2")?.y = getObject("ic_nave")?.y!! - 10
            getObject("ic_bullet2")?.x = getObject("ic_nave")?.x!! + 25
        }


        getObject("ic_bg")?.moveByY(1)
        getObject("ic_bg2")?.moveByY(1)

        if(getObject("ic_bg")?.y!! >= 800){
            getObject("ic_bg")?.y = -800
        }

        if(getObject("ic_bg2")?.y!! >= 800){
            getObject("ic_bg2")?.y = -800
        }

        getObject("ic_nave")?.anim(600, 10, true)
    }

    override fun touching(x: Float, y: Float) {
        super.touching(x, y)

        getObject("ic_nave")?.x = x.toInt()
    }
}