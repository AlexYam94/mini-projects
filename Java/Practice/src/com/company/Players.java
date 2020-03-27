package com.company;

import java.util.ArrayList;
import java.util.List;

public class Players implements ISaveable {
    private String name;
    private int hitPoints;
    private int strength;
    private String weapon;

    public Players(String name, int hitPoints, int strength){
        this.hitPoints=hitPoints;
        this.name=name;
        this.strength=strength;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    @Override
    public String toString() {
        return "Players{" +
                "name='" + name + '\'' +
                ", hitPoints=" + hitPoints +
                ", strength=" + strength +
                ", weapon='" + weapon + '\'' +
                '}';
    }

    @Override
    public void Read(List<String> list) {
        if(list!=null&&list.size()>0){
            this.name=list.get(0);
            this.hitPoints = Integer.parseInt(list.get(1));
            this.strength = Integer.parseInt(list.get(2));
            this.weapon = list.get(3);
        }
    }

    @Override
    public List<String> Write() {
        List<String> values = new ArrayList<String>();
        values.add(0,this.name);
        values.add(1,""+this.hitPoints);
        values.add(2,""+this.strength);
        values.add(3,this.weapon);

        return values;
    }
}
