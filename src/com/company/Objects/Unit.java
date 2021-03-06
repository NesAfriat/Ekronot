
package com.company.Objects;

import com.company.Control.MessageHandler;
import com.company.OurPair;

public abstract class Unit extends Tile {
    protected String name;
    protected OurPair health;
    protected int attackPoints;
    protected int defensePoints;
    MessageHandler m;

    public Unit(char type, OurPair position,String name,int _health, int attackPoints, int defensePoints) {
        super(type, position);
        this.name= name;
        this.health=new OurPair(_health,_health);
        this.attackPoints=attackPoints;
        this.defensePoints=defensePoints;
        m=new MessageHandler();
    }
    public boolean isAlive()
    {
        return health.getFirst()>0;
    }

   public abstract void attack (Enemy enemy, int hitpower, GameBoard gb);
    public abstract void attack(Enemy enemy,GameBoard gb);
   public abstract void attack (Player player, GameBoard gb);


}
