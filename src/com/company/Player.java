package com.company;

import com.company.Unit;

import java.util.List;
import java.util.Random;

public abstract class Player extends Unit {
    int exp;
    int playerLevel;
    int abilityRange;

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public Player(char type, OurPair position, String name, int healthPool, int attackPoints, int defensePoints) {
        super(type,position,name, healthPool, attackPoints, defensePoints);
        exp = 0;
        playerLevel = 1;

    }

    public void levelUp() {
        exp=exp-50*playerLevel;
        playerLevel=playerLevel+1;
        health.setSecond(health.getSecond()+(10*playerLevel));
        health.setFirst(health.getSecond());
        attackPoints=attackPoints+4*playerLevel;
        defensePoints=defensePoints+playerLevel;
    }

    public abstract void onGameTick();



    public void abilityCast(List<MyObserver> enemies)
    {}

    @Override
    public String toString() {
        return ("your level is:"+playerLevel+ " your health is:"+health.getFirst()+" out of "+health.getSecond()+" your attackP is: "+attackPoints+" your defenseP is: "+defensePoints);
    }




    public void accept(VisitorMovement movementVisitor,Player player)
    {
        movementVisitor.visit(this,player);
    }
    public void accept(VisitorMovement movementVisitor,Enemy enemy)
    {
        movementVisitor.visit(this,enemy);
    }
    public void accept (VisitorMovement movementVisitor,Tile tile)
    {
        movementVisitor.visit(this,tile);
    }

    public void attack(Enemy enemy, int hitPower) // player attacks,enemy defends
    {
        Random random=new Random();
        //player roll attack points
        int rollAttack=hitPower;
        //enemy roll defense points
        int rollDefense=random.nextInt(enemy.defensePoints);
        int diff=rollAttack-rollDefense;

        if (diff>0)
        {
            enemy.health.setFirst(enemy.health.getFirst()-diff);//check if enemy died

            if (!enemy.isAlive()) {
                this.exp += enemy.getExpValue();
                if (this.exp >= 50) {
                    this.levelUp();
                }
            }

        }
    }
    public void attack(Enemy enemy)
    {
        Random random=new Random();
        //player roll attack points
        int rollAttack=random.nextInt(this.attackPoints);
        //enemy roll defense points
        int rollDefense=random.nextInt(enemy.defensePoints);
        int diff=rollAttack-rollDefense;

        if (diff>0)
        {
            enemy.health.setFirst(enemy.health.getFirst()-diff);
            if (!enemy.isAlive())
            {
                this.exp+=enemy.getExpValue();
                if (this.exp>=50)
                {
                    this.levelUp();
                }
                OurPair temp=enemy.getPosition();
                enemy.setPosition(this.getPosition());
                this.setPosition(temp);

            }
        }
    }

    @Override
    public void attack(Player player) {

    }
}