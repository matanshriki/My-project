package Test;

import static org.junit.Assert.*;
import model.algorithm.State;
import model.domains.MazeH;
import model.domains.MazeState;

import org.junit.Test;

public class TestBFS
{

	@Test
	public void test1()
	{
		double expected = 5;
		State state = new MazeState("(0,0)");
		State goal = new MazeState("(3,4)");
		double actual = new MazeH().getEvaluation(state, goal);
		assertEquals(expected, actual, 0.1);
	}
	
	@Test
	public void test2()
	{
		
	}
	
	@Test
	public void test3()
	{
		
	}
	
	@Test
	public void test4()
	{
		
	}

}
