package pkgGame;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import eNum.eBonusType;
import pkgHelper.Util;

public class BoardLab6Test {

	@Test
	public void TestBoard1() {
		Util.PrintStart(new Throwable().getStackTrace()[0].getMethodName());
		Board b = new Board();
		Space[] Row = b.getPuzzle()[0];
		assertEquals(15,Row.length);
		Util.PrintEnd(new Throwable().getStackTrace()[0].getMethodName());		
	}
	
	@Test
	public void TestBoard2()
	{
		Util.PrintStart(new Throwable().getStackTrace()[0].getMethodName());
		Board b = new Board();
		Space s = b.getPuzzle()[0][0];
		assertTrue(s.getClass().toString().equals("class pkgGame.Space"));
		
		BonusSquare bs = b.getPuzzle()[0][0].getBonusSquare();
		assertEquals(eBonusType.TripleWord,bs.getBonusType());
		Util.PrintEnd(new Throwable().getStackTrace()[0].getMethodName());		
	}
	
	@Test
	public void TestBoard3()
	{
		Util.PrintStart(new Throwable().getStackTrace()[0].getMethodName());
		Board b = new Board();
		Space[][] puzzle = b.getPuzzle();
		for (int Row = 0; Row < puzzle.length; Row++)
		{
			for (int Col=0; Col< puzzle[Row].length; Col++)
			{
				assertNull(puzzle[Row][Col].getLetter());
			}
		}
		Util.PrintEnd(new Throwable().getStackTrace()[0].getMethodName());		
	}	
	
	@Test
	public void TestBoard4()
	{
		Util.PrintStart(new Throwable().getStackTrace()[0].getMethodName());
		Board b = new Board();
		Space[][] puzzle = b.getPuzzle();
		HashMap<eBonusType, Integer> bonusMap = new HashMap<eBonusType,Integer>();
		for (int Row = 0; Row < puzzle.length; Row++)
		{
			for (int Col=0; Col< puzzle[Row].length; Col++)
			{
				BonusSquare bs = puzzle[Row][Col].getBonusSquare();
				if (bs != null)
				{
					eBonusType bt = bs.getBonusType();
					if (bt != null) {					
						Integer Count = 0;
						if (bonusMap.get(bt) == null)
							Count = 1;
						else {
							Count = bonusMap.get(bt) + 1;
						}
							
						bonusMap.put(bt, Count);
					}					
				}
			}
		}
		assertEquals(8, Integer.parseInt(bonusMap.get(eBonusType.TripleWord).toString()));
		assertEquals(12, Integer.parseInt(bonusMap.get(eBonusType.TripleLetter).toString()));
		assertEquals(16, Integer.parseInt(bonusMap.get(eBonusType.DoubleWord).toString()));
		assertEquals(24, Integer.parseInt(bonusMap.get(eBonusType.DoubleLetter).toString()));
		
		Util.PrintEnd(new Throwable().getStackTrace()[0].getMethodName());	
	}

}
