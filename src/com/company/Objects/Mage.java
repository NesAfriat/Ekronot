
package com.company.Objects;


import com.company.OurPair;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class Mage extends Player {

    OurPair mana;//<manaPool,currentMana>
    int manaCost;
    int spellPower;
    int hitsCount;

    public Mage(char type,OurPair position,String name,int healthPool,int attackPoints, int defensePoints,int manaPool,int manaCost,int spellPower,int hitsCount,int abilityRange)
    {
            super(type, position, name, healthPool, attackPoints, defensePoints);
            this.mana = new OurPair((manaPool / 4),manaPool);
            this.manaCost = manaCost;
            this.spellPower = spellPower;
            this.hitsCount = hitsCount;
            this.abilityRange = abilityRange;

    }

    @Override
    public void levelUp() {
        super.levelUp();
        mana.setSecond(mana.getSecond()+(25*playerLevel));
        mana.setFirst(Math.min(mana.getFirst()+(mana.getSecond()/4),mana.getSecond()));
        this.spellPower=this.spellPower+(10*playerLevel);
        m.sendMessage(this.name +" leveled up and gained:+"+ 10*playerLevel+" Health ,+"+ 4*playerLevel+ " Attack ,+"+1*playerLevel + " Defense ,+ "+25*playerLevel+" Mana , +"+10*playerLevel+" Spell power");


    }

    @Override
    public void onGameTick() {
        mana.setFirst(Math.min(mana.getSecond(),mana.getFirst()+playerLevel));
    }

    @Override

    //casting spacial ability if possible
    public void abilityCast(List<Enemy> enemies, GameBoard gb) {
        if (mana.getFirst() < manaCost)
            m.sendMessage(this.name+" tried to cast Blizzard but there was not enough mana: "+mana.getFirst()+"/"+mana.getSecond());
        else {
            m.sendMessage(this.name +" cast Blizzard.");
            mana.setFirst(mana.getFirst() - manaCost);
            int hits = 0;
            LinkedList<Enemy> releventEnemies=enemyInRange(enemies);
            while (hits < hitsCount&!releventEnemies.isEmpty())
            {
                Random random=new Random();
                Enemy randomEnemy =releventEnemies.get(random.nextInt(releventEnemies.size()));
                this.attack(randomEnemy,spellPower,gb);
                hits++;
            }

        }
    }
    //picking only enemies within abilityRange
    private LinkedList<Enemy> enemyInRange(List<Enemy> enemies)
    {
        LinkedList<Enemy> releventEnemies=new LinkedList<>();
        for (Enemy mo:enemies)
        {
            if (this.getPosition().Range(mo.getPosition())<abilityRange)
            {
                releventEnemies.add(mo);
            }
        }
        return releventEnemies;
    }
    //Providing player stats
    public String describe() {
        return (this.name+ "     Health:"+health.getFirst()+"/"+health.getSecond()+"  Attack: "+attackPoints+"   Defense: "+defensePoints+"   Level: "+playerLevel+"    Experience: "+this.exp+"/"+50*playerLevel+"    Mana: "+mana.getFirst()+"/"+mana.getSecond()+"    Spell power: "+spellPower);

    }
}

