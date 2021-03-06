package com.company.Objects;

import com.company.*;

import java.util.Random;


public class Enemy extends Unit {
    private int expValue;
    public Enemy(char type, OurPair position, int exp, String name, int health, int attackPoints, int defensePoints)
    {
        super(type, position, name, health, attackPoints, defensePoints);
        this.expValue=exp;
    }

    public void setExpValue(int expValue) {
        this.expValue = expValue;
    }
    public int getExpValue() {
        return expValue;
    }
    public int getDefensePoints()
    {
        return defensePoints;
    }
    public String GetName()
    {
        return name;
    }

    @Override
    public void accept(VisitorMovement movementVisitor, Player player)
    {
        movementVisitor.visit(this,player);
    }


    @Override
    public Enemy getEnemy() {
        return this;
    }

    @Override
    public void attack(Enemy enemy, int hitpower, GameBoard gb) {

    }

    @Override
    public void attack(Enemy enemy,GameBoard gb) {

    }

    @Override
    //Enemy attack,player defends. Each side roll a number.
    public void attack(Player player,GameBoard gb) {
        m.sendMessage(this.name+ " engaged in combat with "+player.getName());
        Random random=new Random();
        //player roll attack points
        int rollAttack=random.nextInt(this.attackPoints);
        m.sendMessage(this.name + " rolled "+rollAttack+" attack points");
        //enemy roll defense points
        int rollDefense=random.nextInt(player.getDefencePoints());
        m.sendMessage(player.getName()+ " rolled "+rollDefense+ " defense points" );

        int diff=rollAttack-rollDefense;
        if (diff>0)
        {
            m.sendMessage(this.name+" dealt "+diff+" damage to "+player.name);
            player.getHealth().setFirst(player.getHealth().getFirst()-diff);
            if (!player.isAlive())
            {
                m.sendMessage(player.name+" was killed by "+this.name);
            }

        }
    }
    public String describe()
    {
        return "";
    }


}
