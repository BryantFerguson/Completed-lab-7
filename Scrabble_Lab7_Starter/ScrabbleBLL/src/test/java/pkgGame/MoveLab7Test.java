package pkgGame;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import eNum.eMoveResult;

public class MoveLab7Test {

	@Test
	public void TestMovesNotAdjacent1() {
		
		Board b = new Board();
		Space s1 = new Space(new Letter('A'),0,0);
		Space s2 = new Space(new Letter('X'),0,1);
		Move m = new Move(b);
		m.AddTile(s1);
		m.AddTile(s2);
		ScoreMove sMove = m.ValidateMove();
		assertEquals(eMoveResult.NoAdjacentOrStar, sMove.findMoveResult());
		
		
	}

	@Test
	public void TestMovesNotInLine1() {
		
		Board b = new Board();
		Space s1 = new Space(new Letter('A'),7,7);
		Space s2 = new Space(new Letter('X'),4,3);
		Move m = new Move(b);
		m.AddTile(s1);
		m.AddTile(s2);
		ScoreMove sMove = m.ValidateMove();
		assertEquals(eMoveResult.NotALine, sMove.findMoveResult());		

		
	}
	
	@Test
	public void TestMoveNoTiles1() {
		
		Board b = new Board();
		Move m = new Move(b);
		ScoreMove sMove = m.ValidateMove();
		assertEquals(eMoveResult.NoTiles, sMove.findMoveResult());				
		
	}	
	
	@Test
	public void TestSpaceInWord() {
		
		Board b = new Board();
		b.SetSpace(new Space(new Letter('T'), 7, 6));
		b.SetSpace(new Space(new Letter('H'), 7, 7));
		b.SetSpace(new Space(new Letter('E'), 7, 8));
		
		Move m = new Move(b);
		m.AddTile(new Space(new Letter('E'), 7, 9));
		m.AddTile(new Space(new Letter('E'), 7, 11));
		
		ScoreMove sMove = m.ValidateMove();
		assertEquals(eMoveResult.SpaceInWord, sMove.findMoveResult());	

	}

	@Test
	public void TestLegalMove1() {
		
		//	Empty board, add the first word
		Board b = new Board();
		Space s1 = new Space(new Letter('A'),7,7);
		Space s2 = new Space(new Letter('X'),7,8);
		Space s3 = new Space(new Letter('E'),7,9);
		Move m = new Move(b);
		m.AddTile(s1);
		m.AddTile(s2);
		m.AddTile(s3);
		ScoreMove sMove = m.ValidateMove();
		assertEquals(eMoveResult.GoodMove, sMove.findMoveResult());	
		
		
	}
	
	@Test
	public void TestLegalMove2() {

		// Board has tiles,
		Board b = new Board();
		b.SetSpace(new Space(new Letter('T'), 7, 6));
		b.SetSpace(new Space(new Letter('H'), 7, 7));
		b.SetSpace(new Space(new Letter('E'), 7, 8));

		Space s4 = new Space(new Letter('E'),7,9);
		Move m = new Move(b);
		m.AddTile(s4);
		

		ScoreMove sMove = m.ValidateMove();
		assertEquals(eMoveResult.GoodMove, sMove.findMoveResult());
	}



	@Test
	public void TestBadWord1() {

		// Board has tiles,
		Board b = new Board();
		b.SetSpace(new Space(new Letter('R'), 7, 7));
		b.SetSpace(new Space(new Letter('A'), 7, 8));
		b.SetSpace(new Space(new Letter('I'), 7, 9));
		b.SetSpace(new Space(new Letter('N'), 7, 10));

		Move m = new Move(b);
		m.AddTile(new Space(new Letter('F'), 8, 6));
		m.AddTile(new Space(new Letter('A'), 8, 7));
		m.AddTile(new Space(new Letter('D'), 8, 8));

		ScoreMove sMove = m.ValidateMove();
		assertEquals(eMoveResult.NotAWord, sMove.findMoveResult());
	}

	@Test
	public void TestLegalMove3() {

		// Board has tiles,
		Board b = new Board();
		b.SetSpace(new Space(new Letter('R'), 7, 7));
		b.SetSpace(new Space(new Letter('A'), 7, 8));
		b.SetSpace(new Space(new Letter('I'), 7, 9));
		b.SetSpace(new Space(new Letter('N'), 7, 10));

		Move m = new Move(b);
		m.AddTile(new Space(new Letter('F'), 8, 6));
		m.AddTile(new Space(new Letter('E'), 8, 7));
		m.AddTile(new Space(new Letter('D'), 8, 8));

		ScoreMove sMove = m.ValidateMove();
		assertEquals(eMoveResult.GoodMove, sMove.findMoveResult());
		ScoreMove sm = m.getScoremove();
		
		int iScoreMove = sm.CalculateScoreMove();
		
		assertEquals(20,iScoreMove);
		
 		for (ScoreWord sw : sm.getArrScoreWord()) {
			for (Space s : sw.getTiles()) {
				System.out.print(s.getLetter().getChLetter());
			}
			System.out.println(" score: " + sw.getScore());
		}
	}
	
	
	@Test
	public void TestLegalMove4() {

		// Board has tiles,
		Board b = new Board();
		b.SetSpace(new Space(new Letter('R'), 7, 7));
		b.SetSpace(new Space(new Letter('A'), 7, 8));
		b.SetSpace(new Space(new Letter('I'), 7, 9));
		b.SetSpace(new Space(new Letter('N'), 7, 10));
		b.SetSpace(new Space(new Letter('F'), 8, 6));
		b.SetSpace(new Space(new Letter('E'), 8, 7));
		b.SetSpace(new Space(new Letter('D'), 8, 8));
		
		Move m = new Move(b);
		m.AddTile(new Space(new Letter('R'), 9, 4));
		m.AddTile(new Space(new Letter('O'), 9, 5));
		m.AddTile(new Space(new Letter('A'), 9, 6));
		m.AddTile(new Space(new Letter('D'), 9, 7));		

		ScoreMove sMove = m.ValidateMove();
		assertEquals(eMoveResult.GoodMove, sMove.findMoveResult());
		ScoreMove sm = m.getScoremove();
		
		int iScoreMove = sm.CalculateScoreMove();
		
		assertEquals(16,iScoreMove);
		
 		for (ScoreWord sw : sm.getArrScoreWord()) {
			for (Space s : sw.getTiles()) {
				System.out.print(s.getLetter().getChLetter());
			}
			System.out.println(" score: " + sw.getScore());
		}
	}
	
	@Test
	public void TestLegalMakeMove1() {

		// Board has tiles,
		Board b = new Board();
		b.SetSpace(new Space(new Letter('R'), 7, 7));
		b.SetSpace(new Space(new Letter('A'), 7, 8));
		b.SetSpace(new Space(new Letter('I'), 7, 9));
		b.SetSpace(new Space(new Letter('N'), 7, 10));
		
		Move m1 = new Move(b);
		m1.AddTile(new Space(new Letter('F'), 8, 6));
		m1.AddTile(new Space(new Letter('E'), 8, 7));
		m1.AddTile(new Space(new Letter('D'), 8, 8));
		b.MakeMove(m1);
		
		Move m2 = new Move(b);
		m2.AddTile(new Space(new Letter('R'), 9, 4));
		m2.AddTile(new Space(new Letter('O'), 9, 5));
		m2.AddTile(new Space(new Letter('A'), 9, 6));
		m2.AddTile(new Space(new Letter('D'), 9, 7));		
		b.MakeMove(m2);
		
		assertEquals(b.getMovesMade().size(),2);
		
		for (Move m: b.getMovesMade())
		{
			System.out.println(m.getScoremove().CalculateScoreMove());
		}
		/*
		ScoreMove sMove = m.ValidateMove();
		assertEquals(eMoveResult.GoodMove, sMove.findMoveResult());
		ScoreMove sm = m.getScoremove();
		
		int iScoreMove = sm.CalculateScoreMove();
		
		assertEquals(16,iScoreMove);
		
 		for (ScoreWord sw : sm.getArrScoreWord()) {
			for (Space s : sw.getTiles()) {
				System.out.print(s.getLetter().getChLetter());
			}
			System.out.println(" score: " + sw.getScore());
		}
		*/
	}

}
