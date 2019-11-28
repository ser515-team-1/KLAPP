package com.asu.ser.klapp.models;

public class ImagePair {

    private int thing;
    private int target;

    public ImagePair(int thing, int target){
        this.thing = thing;
        this.target = target;
    }

    public int getThing() {
        return thing;
    }

    public void setThing(int thing) {
        this.thing = thing;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }
}
