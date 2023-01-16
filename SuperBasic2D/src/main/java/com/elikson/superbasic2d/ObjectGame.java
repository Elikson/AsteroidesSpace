package com.elikson.superbasic2d;

import android.content.Context;
import android.graphics.BitmapFactory;

/**
 * Created by Elikson Bastos on 10/11/2018.
 */

public class ObjectGame {
    private int width;
    private int height;

    private int sourceId;
    private int originalWidth;
    private int originalHeight;

    private int sourcesIds[];

    private int x;
    private int y;
    private int yOrigem;
    private int yOldOrigem;
    private int delayY = 0;
    private int delayX = 0;

    private int oldWidth;
    private int oldHeight;
    private float oldScale;
    private boolean visibility = true;

    private int durationAnim = -1;
    private int delayAnim = -1;
    private boolean executeAnim = true;
    private int indexAnim = 0;

    private float scale;

    private String tag;
    private String text;

    private boolean moveToUp = false;
    private boolean moveToDown = false;
    private boolean moveToRight = false;
    private boolean moveToLeft = false;
    private boolean move = false;

    private Context context;

    public ObjectGame(Context context, int sourceId, int x, int y, int width, int height, String tag){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.oldWidth = width;
        this.oldHeight = height;
        this.sourceId = sourceId;
        this.tag = tag;

        BitmapFactory.Options dimension = new BitmapFactory.Options();
        dimension.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(), sourceId, dimension);
        originalWidth = dimension.outWidth;
        originalHeight =  dimension.outHeight;

        this.context = context;
    }

    public ObjectGame(Context context, int sourcesIds[], int x, int y, int width, int height, String tag){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.oldWidth = width;
        this.oldHeight = height;
        this.sourcesIds = sourcesIds;
        this.sourceId = sourcesIds[0];
        this.tag = tag;

        BitmapFactory.Options dimension = new BitmapFactory.Options();
        dimension.inJustDecodeBounds = true;

        BitmapFactory.decodeResource(context.getResources(), sourceId, dimension);
        originalWidth = dimension.outWidth;
        originalHeight =  dimension.outHeight;

        this.context = context;
    }

    public ObjectGame(Context context, int sourceId, int x, int y, int width, int height, String tag, boolean visibility){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.oldWidth = width;
        this.oldHeight = height;
        this.sourceId = sourceId;
        this.tag = tag;

        if(!visibility){
            setVisibility(false);
        }

        BitmapFactory.Options dimension = new BitmapFactory.Options();
        dimension.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(), sourceId, dimension);
        originalWidth = dimension.outWidth;
        originalHeight =  dimension.outHeight;

        this.context = context;
    }

    public ObjectGame(Context context, int sourcesIds[], int x, int y, int width, int height, String tag, boolean visibility){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.oldWidth = width;
        this.oldHeight = height;
        this.sourcesIds = sourcesIds;
        this.sourceId = sourcesIds[0];
        this.tag = tag;

        if(!visibility){
            setVisibility(false);
        }

        BitmapFactory.Options dimension = new BitmapFactory.Options();
        dimension.inJustDecodeBounds = true;

        BitmapFactory.decodeResource(context.getResources(), sourceId, dimension);
        originalWidth = dimension.outWidth;
        originalHeight =  dimension.outHeight;

        this.context = context;
    }

    public ObjectGame(int sourceId, int x, int y, float scale, String text, String tag, boolean visibility){
        this.x = x;
        this.y = y;
        this.sourceId = sourceId;
        this.tag = tag;
        this.text = text;
        this.scale = scale;
        this.oldScale = scale;
        this.oldWidth = width;
        this.oldHeight = height;

        if(!visibility){
            setVisibilityText(false);
        }
    }

    public ObjectGame(int sourceId, int x, int y, float scale, String text, String tag){
        this.x = x;
        this.y = y;
        this.sourceId = sourceId;
        this.tag = tag;
        this.text = text;
        this.scale = scale;
        this.oldScale = scale;
        this.oldWidth = width;
        this.oldHeight = height;
    }

    public ObjectGame(){}


    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
        this.oldScale = scale;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getyOrigem() {
        return yOrigem;
    }

    public int getyOldOrigem() {
        return yOldOrigem;
    }

    public void setyOldOrigem(int yOldOrigem) {
        this.yOldOrigem = yOldOrigem;
    }

    public void setyOrigem(int yOrigem) {
        this.yOrigem = yOrigem;
    }

    public boolean isMove() {
        return move;
    }

    public void setMove(boolean move) {
        this.move = move;
    }

    public boolean isMoveToUp() {
        return moveToUp;
    }

    public void setMoveToUp(boolean moveToUp) {
        this.moveToUp = moveToUp;
    }

    public boolean isMoveToDown() {
        return moveToDown;
    }

    public void setMoveToDown(boolean moveToDown) {
        this.moveToDown = moveToDown;
    }

    public boolean isMoveToRight() {
        return moveToRight;
    }

    public void setMoveToRight(boolean moveToRight) {
        this.moveToRight = moveToRight;
    }

    public boolean isMoveToLeft() {
        return moveToLeft;
    }

    public void setMoveToLeft(boolean moveToLeft) {
        this.moveToLeft = moveToLeft;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void moveByX(int x){
        this.x += x;
    }

    public void moveByX(int x, int delayX){
        if(this.delayX <= 0){
            this.delayX = delayX;
            this.x += x;
        }
        this.delayX--;
    }

    public void moveByY(int y){
        this.y += y;
    }

    public void moveByY(int y, int delayY){
        if(this.delayY <= 0){
            this.delayY = delayY;
            this.y += y;
        }
        this.delayY--;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
        this.oldWidth = width;
    }

    public int getHeight() {
        return height;
    }

    public int getSourceId() {
        return sourceId;
    }

    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }

    public int getOriginalWidth() {
        return originalWidth;
    }

    public int getOriginalHeight() {
        return originalHeight;
    }

    public void setHeight(int height) {
        this.height = height;
        this.oldHeight = height;
    }

    public int[] getSourcesIds() {
        return sourcesIds;
    }

    public void setSourcesIds(int[] sources) {
        sourcesIds = sources;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean option){
        if(option){
            width = oldWidth;
            height = oldHeight;
            visibility = true;
        }else{
            width = 0;
            height = 0;
            visibility = false;
        }
    }

    public void setVisibilityText(boolean option){
        if(option){
            scale = oldScale;
            visibility = true;
        }else{
            scale = 0;
            visibility = false;
        }
    }

    public void reDoOriginalsValues(){
        BitmapFactory.Options dimension = new BitmapFactory.Options();
        dimension.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(), sourceId, dimension);
        originalWidth = dimension.outWidth;
        originalHeight =  dimension.outHeight;
    }

    public boolean anim(int duration, int delay, boolean repeat){
        if(executeAnim){
            if(durationAnim == -1){
                durationAnim = duration;
            }
            if(delayAnim == -1){
                delayAnim = delay;
            }

            if(durationAnim > 0){
                if(delayAnim == 0){
                    sourceId = sourcesIds[indexAnim];
                    if(indexAnim != sourcesIds.length-1){
                        indexAnim++;
                    }else{
                        indexAnim = 0;
                    }
                }

                delayAnim--;
                durationAnim--;
            }else{
                executeAnim = repeat;
                durationAnim = duration;
                if(!repeat){
                    return true;
                }
            }
        }
        return false;
    }

    public void resetAnim(){
        durationAnim = -1;
        delayAnim = -1;
        executeAnim = true;
        indexAnim = 0;
        if(sourcesIds != null){
            sourceId = sourcesIds[0];
        }
    }

    public void turnRight(){
        if(originalWidth < 0){
            originalWidth = originalWidth * -1;
        }
    }

    public void turnLeft(){
        if(originalWidth > 0){
            originalWidth = -originalWidth;
        }
    }

    public boolean getDirectionSide(){
        return originalWidth > 0;
    }

    public void turnUp(){
        originalHeight = originalHeight < 0 ? +originalHeight : originalHeight;
    }

    public void turnDown(){
        originalHeight = originalHeight > 0 ? -originalHeight : originalHeight;
    }
}
