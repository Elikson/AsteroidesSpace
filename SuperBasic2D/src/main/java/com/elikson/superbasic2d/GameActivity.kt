package com.elikson.superbasic2d

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.ActivityInfo
import android.graphics.Rect
import android.opengl.GLSurfaceView
import android.os.Bundle
import android.view.MotionEvent
import com.twicecircled.spritebatcher.Drawer
import com.twicecircled.spritebatcher.SpriteBatcher
import javax.microedition.khronos.opengles.GL10

open class GameActivity() : Activity(), Drawer {

    val objectGames: MutableList<ObjectGame> = mutableListOf()
    private var viewWidth: Int = 0
    private var viewHeight: Int = 0
    var touched = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val surfaceView: GLSurfaceView = getSurfaceView()
        setContentView(surfaceView)
    }

    @SuppressLint("SourceLockedOrientationActivity")
    fun getSurfaceView(): GLSurfaceView {
        val surfaceView = GLSurfaceView(this)

        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        var lenghtArraysIds = 0
        for (objectGame in objectGames) {
            if (objectGame.sourcesIds != null) {
                lenghtArraysIds += objectGame.sourcesIds.size
            }
        }
        val resourceIds = IntArray(objectGames.size + lenghtArraysIds)

        var indexId = 0
        for (objectGame in objectGames) {
            resourceIds[indexId] = objectGame.sourceId
            indexId++
            val auxIds = objectGame.sourcesIds
            if (auxIds != null) {
                for (id in auxIds) {
                    resourceIds[indexId] = id
                    indexId++
                }
            }
        }

        surfaceView.setRenderer(SpriteBatcher(this, resourceIds, this))
        return surfaceView
    }

    fun getObject(tag: String): ObjectGame? {
        return objectGames.find { predicate: ObjectGame -> predicate.tag.equals(tag) }
    }

    fun getObject(position: Int): ObjectGame? {
        return objectGames[position]
    }

    fun draw(sb: SpriteBatcher) {
        //Desenhando o que foi declarado
        for (objectGame in objectGames) {
            if (objectGame.tag.contains("text")) {
                sb.drawText(
                    objectGame.sourceId,
                    objectGame.text,
                    objectGame.x,
                    objectGame.y,
                    objectGame.scale
                )
            } else {
                sb.draw(
                    objectGame.sourceId,
                    Rect(0, 0, objectGame.originalWidth, objectGame.originalHeight),
                    objectGame.x,
                    objectGame.y,
                    Rect(0, 0, objectGame.width, objectGame.height),
                    0,
                    1.0f
                )
            }
        }
        //
    }

    open fun drawing(){
    }

    open fun touching(x: Float, y: Float) {

    }

    open fun isTouch(posx: Float, posy: Float, pos: Int): Boolean {
        val objectGame = objectGames[pos]
        return (posx >= objectGame.x && posx <= objectGame.x + objectGame.width
                && posy >= objectGame.y + objectGame.height / 3 && posy <= objectGame.y + objectGame.height + objectGame.height / 2)
    }

    open fun isTouch(posx: Float, posy: Float, tag: String): Boolean {
        val objectGame = objectGames.find { predicate: ObjectGame -> predicate.tag.equals(tag) }!!
        return (posx >= objectGame.x && posx <= objectGame.x + objectGame.width
                && posy >= objectGame.y + objectGame.height / 3 && posy <= objectGame.y + objectGame.height + objectGame.height / 2)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        event.setLocation(
            event.x * Configs.Screen.SCREEN_WIDTH / viewWidth,
            event.y * Configs.Screen.SCREEN_HEIGHT / viewHeight
        )

        if(event.getAction() == MotionEvent.ACTION_DOWN){
            touched = true;
        }

        if(event.getAction() == MotionEvent.ACTION_UP){
            touched = false;
        }

        val auxX = event.x
        val auxY = event.y

        touching(auxX, auxY)
        return true
    }

    override fun onDrawFrame(gl10: GL10, spriteBatcher: SpriteBatcher) {
        //Tamanho da tala
        viewWidth = spriteBatcher.viewWidth
        viewHeight = spriteBatcher.viewHeight

        //Calcula a escala X referente a largura da tela de exibição do jogo
        val scaleX: Float = viewWidth * 1.0f / Configs.Screen.SCREEN_WIDTH.toFloat()

        //Calcula a escala Y referente a altura da tela de exibição do jogo
        val scaleY: Float = viewHeight * 1.0f / Configs.Screen.SCREEN_HEIGHT.toFloat()

        //Redimensiona a tela de exibição de acordo com o tamanho da tela do dispositivo
        //baseado no cálculo das escalas X e Y
        gl10.glScalef(scaleX, scaleY, 1.0f)

        draw(spriteBatcher)

        drawing()
    }
}