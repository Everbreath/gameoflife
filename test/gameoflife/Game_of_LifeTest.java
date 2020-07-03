package gameoflife;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
@RunWith(JUnit4.class)
public class Game_of_LifeTest
{
	Game_of_Life life;
	@Before
	public void setUp()
	{
		int[][]a= {{0,0,0,0,0},{0,1,0,1,0},{0,1,1,0,0},{0,0,0,1,0},{0,0,0,0,0}};
		life=new Game_of_Life(3,3);
		life.setcellworld(a);
	}
	@Test
	public void testgetcellworld()
	{
		int[][]a= {{0,0,0,0,0},{0,1,0,1,0},{0,1,1,0,0},{0,0,0,1,0},{0,0,0,0,0}};
		assertArrayEquals(a,life.getcellworld());
	}
	@Test
	public void testsetcellworld()
	{
		int[][]a= {{0,0,0,0,0},{0,1,0,1,0},{0,1,1,0,0},{0,0,0,1,0},{0,0,0,0,0}};
		assertArrayEquals(a,life.cellworld);
	}
	@Test
	public void testgetNeighborCount()
	{
		assertEquals(2,life.getNeighborCount(1, 1));
		assertEquals(4,life.getNeighborCount(1, 2));
		assertEquals(1,life.getNeighborCount(1, 3));
		assertEquals(2,life.getNeighborCount(2, 1));
		assertEquals(4,life.getNeighborCount(2, 2));
		assertEquals(3,life.getNeighborCount(2, 3));
		assertEquals(2,life.getNeighborCount(3, 1));
		assertEquals(3,life.getNeighborCount(3, 2));
		assertEquals(1,life.getNeighborCount(3, 3));
	}
	@Test
	public void testupdate()
	{
		int[][]a= {{0,0,0,0,0},{0,1,0,0,0},{0,1,0,1,0},{0,0,1,0,0},{0,0,0,0,0}};
		life.update();
		assertArrayEquals(a,life.getcellworld());
	}
	@After
	public void teardown()
	{
		System.out.println("teardown");
		life=null;
	}
}


