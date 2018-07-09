/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements.levelcomponents;

import TiposGenerales.ContainerS;
import TiposGenerales.DinamicObject;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author mcdre
 */
public class Bullet
{
	protected Vector2f pos;
	protected Vector2f dir;
	protected int lived = 0;
	
	protected boolean active = true;
	
	protected int tiempoVida = 2000;
	protected int radioBala = 100;
	protected int Danno = 5;
	
	public Bullet ( Vector2f pos, Vector2f dir )
	{
		this.pos = pos;
		this.dir = dir;
		
		dir.scale(500);
	}
	
	public Bullet init ( Vector2f pos, Vector2f dir )
	{
		this.pos = pos;
		this.dir = dir;
		
		dir.scale(500);
		setActive(true);
		return this;
	}
	
	public Bullet ()
	{
		active = false;
	}
	
	public void update( int t )
	{
		if( active )
		{
			Vector2f realSpeed = dir.copy();
			realSpeed.scale( (t/1000.0f) );
			pos.add( realSpeed );
			
			lived += t;
			if( lived > tiempoVida ) active = false;
		}
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException 
	{
		if( active )
		{
			g.setColor(Color.blue);
			g.fillRect(pos.getX()-10, pos.getY()-10, 20, 20);
		}
	}
	
	public boolean getActive()
	{
		return active;
	}
	
	public void setActive( boolean active )
	{
		this.active = active;
	}
	
	// A little circle-circle collision detection
	public boolean collideWith ( Vector2f otherPos , int otherRadiusSqared )
	{
		int dis = (int) otherPos.copy().sub(pos).lengthSquared();
		
		if( dis < ( otherRadiusSqared + radioBala ) )
		{
			return true;
		}
		return false;
	}
	
	public int getDamage ()
	{
		return Danno;
	}
	
}