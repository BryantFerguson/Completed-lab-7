package pkgGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import eNum.eMoveResult;
import eNum.eMoveType;
import pkgCore.Dictionary;
import pkgCore.Word;

public class Move {

	private Board b;
	private HashSet<Space> tiles = new HashSet<Space>();
	private Dictionary d = new Dictionary();
	private ScoreMove scoremove = new ScoreMove();

	public Move(Board B) {
		this.b = B;
	}

	protected ArrayList<Space> getTiles() {
		return new ArrayList<Space>(tiles);
	}

	public void AddTile(Space s) {
		tiles.add(s);
	}

	public void SetTiles(ArrayList<Space> spaces) {
		tiles.clear();
		tiles.addAll(spaces);
	}

	public ScoreMove getScoremove() {
		return scoremove;
	}

	public ScoreMove ValidateMove() {

		HashMap<Integer, Integer> hmRow = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> hmCol = new HashMap<Integer, Integer>();
		ArrayList<Space> spaces = new ArrayList<Space>(this.tiles);
		ArrayList<Space> moveSpaces;
		Space minSpace = null;
		Space maxSpace = null;
		Space minBoardSpace = null;
		Space maxBoardSpace = null;
		eMoveType moveType;

		int iRowCnt = 0;
		int iColCnt = 0;

		// Check to see if:
		// The board is null
		// There are no tiles
		// If the move is Horizontal or Vertical
		// If the move is a straight line

		// Is board valid (not null)?
		if (b == null) {
			scoremove.AddScoreWord(new ScoreWord(d, spaces, b, eMoveResult.MissingBoard));
			return scoremove;
		}

		// What if there are no tiles?
		if (tiles.size() == 0) {
			scoremove.AddScoreWord(new ScoreWord(d, spaces, b, eMoveResult.NoTiles));
			return scoremove;
		}

		// Area all tiles either vertical or horizontal?
		for (int i = 0; i < spaces.size(); i++) {
			if (hmRow.get(spaces.get(i).getRow()) == null)
				iRowCnt = 1;
			else
				iRowCnt = hmRow.get(spaces.get(i).getRow()) + 1;

			if (hmCol.get(spaces.get(i).getCol()) == null)
				iColCnt = 1;
			else
				iColCnt = hmCol.get(spaces.get(i).getCol()) + 1;
			hmRow.put(spaces.get(i).getRow(), iRowCnt);
			hmCol.put(spaces.get(i).getCol(), iColCnt);
		}

		if (hmRow.size() == 1)
			moveType = eMoveType.HORIZONTAL; // eMoveResult.RowMove;
		else if (hmCol.size() == 1)
			moveType = eMoveType.VERTICAL; // eMoveResult.ColMove;
		else {
			scoremove.AddScoreWord(new ScoreWord(d, spaces, b, eMoveResult.NotALine));
			return scoremove;
		}

		// Is each tile moving to a spot that doesn't have an existing Letter?
		if (b.isAnySpaceUsed(spaces)) {
			scoremove.AddScoreWord(new ScoreWord(d, spaces, b, eMoveResult.SpaceUsed));
			return scoremove;
		}

		// Is at least one tile touching an existing tile or star?
		if (!b.isAnySpaceAdjacentOrStar(spaces)) {
			scoremove.AddScoreWord(new ScoreWord(d, spaces, b, eMoveResult.NoAdjacentOrStar));
			return scoremove;
		}

		switch (moveType) {
		case VERTICAL:
			minSpace = spaces.stream().min(Comparator.comparing(Space::getRow))
					.orElseThrow(NoSuchElementException::new);

			maxSpace = spaces.stream().max(Comparator.comparing(Space::getRow))
					.orElseThrow(NoSuchElementException::new);

			minBoardSpace = FindMinMaxSpace(b, minSpace, minSpace.getRow(), minSpace.getCol() - 1, moveType, 0, -1);

			maxBoardSpace = FindMinMaxSpace(b, maxSpace, maxSpace.getRow(), maxSpace.getCol() + 1, moveType, 0, 1);

			break;

		case HORIZONTAL:
			minSpace = spaces.stream().min(Comparator.comparing(Space::getCol))
					.orElseThrow(NoSuchElementException::new);
			maxSpace = spaces.stream().max(Comparator.comparing(Space::getCol))
					.orElseThrow(NoSuchElementException::new);

			minBoardSpace = FindMinMaxSpace(b, minSpace, minSpace.getRow(), minSpace.getCol() - 1, moveType, 0, -1);

			maxBoardSpace = FindMinMaxSpace(b, maxSpace, maxSpace.getRow(), maxSpace.getCol() + 1, moveType, 0, 1);

			break;
		}

		moveSpaces = findWordSpaces(b, minBoardSpace, maxBoardSpace, moveType);
		scoremove.AddScoreWord(new ScoreWord(d, moveSpaces, b, moveType));

		if (scoremove.findMoveResult() != eMoveResult.GoodMove) {
			return scoremove;
		}

		System.out.println("Good word so far");

		// Find every subsequent word made.

		for (Space s : this.tiles) {

			switch (moveType) {
			case VERTICAL:
				minBoardSpace = FindMinMaxSpace(b, s, s.getRow(), s.getCol() + 1,
						(moveType == eMoveType.VERTICAL ? eMoveType.HORIZONTAL : eMoveType.VERTICAL), 0, -1);
				maxBoardSpace = FindMinMaxSpace(b, s, s.getRow(), s.getCol() - 1,
						(moveType == eMoveType.VERTICAL ? eMoveType.HORIZONTAL : eMoveType.VERTICAL), 0, -1);
				break;
			case HORIZONTAL:
				minBoardSpace = FindMinMaxSpace(b, s, s.getRow() - 1, s.getCol(),
						(moveType == eMoveType.VERTICAL ? eMoveType.HORIZONTAL : eMoveType.VERTICAL), 0, -1);
				maxBoardSpace = FindMinMaxSpace(b, s, s.getRow() + 1, s.getCol(),
						(moveType == eMoveType.VERTICAL ? eMoveType.HORIZONTAL : eMoveType.VERTICAL), 0, -1);
			}

			moveSpaces = findWordSpaces(b, minBoardSpace, maxBoardSpace,
					(moveType == eMoveType.VERTICAL ? eMoveType.HORIZONTAL : eMoveType.VERTICAL));
			ScoreWord sw = new ScoreWord(d, moveSpaces, b,
					(moveType == eMoveType.VERTICAL ? eMoveType.HORIZONTAL : eMoveType.VERTICAL));
			if (sw.getMoveResult() != eMoveResult.SingleSpace) {
				scoremove.AddScoreWord(sw);
			}

		}

		// All the words are found, time to score the words
		// If any of the above is NOTAWORD, return NOTAWORD

		return scoremove;
	}

	@SuppressWarnings("incomplete-switch")
	private Space FindMinMaxSpace(Board b, Space MinMaxSpace, int Row, int Col, eMoveType MoveType, int Boundary,
			int IncreaseDecrease) {
		switch (MoveType) {
		case HORIZONTAL:
			if (Row == Boundary)
				if (b.getPuzzle()[Boundary][Col].getLetter() == null)
					return MinMaxSpace;
				else
					return b.getPuzzle()[Boundary][Col];
			else if (Row != Boundary) {
				if (b.getPuzzle()[Row][Col].getLetter() == null) {
					// There's no letter here, return minSpace
					return MinMaxSpace;
				} else if (b.getPuzzle()[Row][Col].getLetter() != null) {
					// Board has a space, back up one space
					return FindMinMaxSpace(b, b.getPuzzle()[Row][Col], Row, Col + IncreaseDecrease, MoveType, Boundary,
							IncreaseDecrease);
				}
			}
			break;
		case VERTICAL:
			if (Col == Boundary)
				if (b.getPuzzle()[Row][Boundary].getLetter() == null)
					return MinMaxSpace;
				else
					return b.getPuzzle()[Row][Boundary];
			else if (Col != Boundary) {
				if (b.getPuzzle()[Row][Col].getLetter() == null) {
					// There's no letter here, return minSpace
					return MinMaxSpace;
				} else if (b.getPuzzle()[Row][Col].getLetter() != null) {
					// Board has a space, back up one space
					return FindMinMaxSpace(b, b.getPuzzle()[Row][Col], Row + IncreaseDecrease, Col, MoveType, Boundary,
							IncreaseDecrease);
				}
			}
			break;
		}
		return MinMaxSpace;
	}

	private Space FindSpace(Board b, int Row, int Col) {
		for (Space s : this.tiles) {
			if (s.getRow() == Row && s.getCol() == Col)
				return s;
		}
		return b.getPuzzle()[Row][Col];
	}

	@SuppressWarnings("incomplete-switch")
	private ArrayList<Space> findWordSpaces(Board b, Space minSpace, Space maxSpace, eMoveType MoveType) {
		ArrayList<Space> SpacesFound = new ArrayList<Space>();
		switch (MoveType) {
		case VERTICAL:
			for (int Row = minSpace.getRow(); Row < maxSpace.getRow() + 1; Row++) {
				SpacesFound.add(FindSpace(b, Row, minSpace.getCol()));
			}
			break;
		case HORIZONTAL:
			for (int Col = minSpace.getCol(); Col < maxSpace.getCol() + 1; Col++) {
				SpacesFound.add(FindSpace(b, minSpace.getRow(), Col));
			}
			break;
		}
		return SpacesFound;
	}
}
