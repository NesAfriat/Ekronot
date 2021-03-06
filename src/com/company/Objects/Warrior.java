package com.company.Objects;

import com.company.OurPair;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Warrior extends Player {
    OurPair cooldown;//<abilityCooldown,remainingCooldown>

    public Warrior(char type,OurPair position,String name,int healthPool,int attackPoints, int defensePoints,int abillityCooldown)
    {
        super(type,position,name, healthPool, attackPoints, defensePoints);
        super.abilityRange=3;
        this.cooldown=new OurPair(0,abillityCooldown);

    }

    @Override
    public void levelUp() {
        super.levelUp();
        cooldown.setFirst(0);
        health.setSecond(health.getSecond()+(5*playerLevel));
        attackPoints=attackPoints+(2*playerLevel);
        defensePoints=defensePoints+playerLevel;
        m.sendMessage(this.name +" leveled up and gained:+"+ 15*playerLevel+" Health ,+"+ 6*playerLevel+ " Attack ,+"+2*playerLevel + "Defense");
    }
    public void onGameTick()
    {
        cooldown.setFirst(Math.max(cooldown.getFirst()-1,0));
    }

    @Override
    //casting spacial ability if possible
    public void abilityCast(List<Enemy> enemies, GameBoard gb) {
        if (cooldown.getFirst()>0)
            m.sendMessage(this.name+" tried to cast Avenger's sheild, but he's not cool enough: "+cooldown.getFirst()+"/"+cooldown.getSecond());
        else {
            m.sendMessage(this.name +"cast Avenger's sheild.");
            cooldown.setFirst(cooldown.getSecond());
            health.setFirst(Math.min(health.getFirst()+(10*defensePoints),health.getSecond()));

            LinkedList<Enemy> releventEnemies=new LinkedList<>();//picking only enemies within range 3
            for (Enemy mo:enemies)
            {
                if (this.getPosition().Range(mo.getPosition())<3)
                {
                    releventEnemies.add(mo);
                }
            }

            Random random=new Random();
            if (!releventEnemies.isEmpty()) {
                Enemy randomEnemy = releventEnemies.get(random.nextInt(releventEnemies.size()));
                this.attack(randomEnemy, health.getSecond() / 10,gb);
            }

        }
    }


    public String describe() {
        return (this.name+ "     Health:"+health.getFirst()+"/"+health.getSecond()+"  Attack: "+attackPoints+"   Defense: "+defensePoints+"   Level: "+playerLevel+"    Experience: "+this.exp+"/"+50*playerLevel+"   Cooldown: "+this.cooldown.getFirst()+"/"+cooldown.getSecond());

    }
}
