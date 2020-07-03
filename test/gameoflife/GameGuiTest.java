package gameoflife;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
@RunWith(JUnit4.class)
public class GameGuiTest {
	GameGui gui;
	@Before
	public void setUp()
	{
		gui=new GameGui("岑涛 刘思成小组");
	}
	@Test
	public void testpanelInit() 
	{
		gui.panelInit();
	}
	@Test
	public void testmakeNextGeneration() 
	{
		gui.makeNextGeneration();
	}
	@After
	public void teardown()
	{
		System.out.println("teardown");
		gui=null;
	}
}
