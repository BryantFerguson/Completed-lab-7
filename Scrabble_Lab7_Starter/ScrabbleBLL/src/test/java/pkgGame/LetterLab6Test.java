package pkgGame;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pkgHelper.Util;

public class LetterLab6Test {

	@Test
	public void TestLetter() {
		Util.PrintStart(new Throwable().getStackTrace()[0].getMethodName());
//		0 Points - Blank tile.
//		1 Point - A, E, I, L, N, O, R, S, T and U.
//		2 Points - D and G.
//		3 Points - B, C, M and P.
//		4 Points - F, H, V, W and Y.
//		5 Points - K.
//		8 Points - J and X.
//		10 Points - Q and Z.

		int iOne = 1;
		int iTwo = 2;
		int iThree = 3;
		int iFour = 4;
		int iFive = 5;
		int iEight = 8;
		int iTen = 10;
		assertEquals(iOne,new Letter('A').getiScore());
		assertEquals(iThree,new Letter('B').getiScore());
		assertEquals(iThree,new Letter('C').getiScore());
		assertEquals(iTwo,new Letter('D').getiScore());
		assertEquals(iOne,new Letter('E').getiScore());
		assertEquals(iFour,new Letter('F').getiScore());
		assertEquals(iTwo,new Letter('G').getiScore());
		assertEquals(iFour,new Letter('H').getiScore());
		assertEquals(iOne,new Letter('I').getiScore());
		assertEquals(iEight,new Letter('J').getiScore());
		assertEquals(iFive,new Letter('K').getiScore());
		assertEquals(iOne,new Letter('L').getiScore());
		assertEquals(iThree,new Letter('M').getiScore());
		assertEquals(iOne,new Letter('N').getiScore());
		assertEquals(iOne,new Letter('O').getiScore());
		assertEquals(iThree,new Letter('P').getiScore());
		assertEquals(iTen,new Letter('Q').getiScore());
		assertEquals(iOne,new Letter('R').getiScore());
		assertEquals(iOne,new Letter('S').getiScore());
		assertEquals(iOne,new Letter('T').getiScore());
		assertEquals(iOne,new Letter('U').getiScore());
		assertEquals(iFour,new Letter('V').getiScore());
		assertEquals(iFour,new Letter('W').getiScore());
		assertEquals(iEight,new Letter('X').getiScore());
		assertEquals(iFour,new Letter('Y').getiScore());
		assertEquals(iTen,new Letter('Z').getiScore());
		
		Util.PrintEnd(new Throwable().getStackTrace()[0].getMethodName());
	}

}
