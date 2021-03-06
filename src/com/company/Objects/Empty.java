package com.company.Objects;

import com.company.OurPair;
import com.company.VisitorMovement;

public class Empty extends Tile {
    public Empty(OurPair position) {
        super('.', position);
    }
    @Override
    public void accept(VisitorMovement vm, Player player) {
        vm.visit(player,this);
    }
    @Override
    public void accept(VisitorMovement vm, Enemy enemy) {
        vm.visit(enemy,this);
    }
    @Override
    public String toString() {
        return ".";
    }
}
