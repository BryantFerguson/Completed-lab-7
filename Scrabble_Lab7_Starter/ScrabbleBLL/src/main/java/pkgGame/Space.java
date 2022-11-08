package pkgGame;

import java.util.Objects;

import pkgCore.Word;

public class Space {

	private BonusSquare bonusSquare;
	private Letter letter;
	private int row;
	private int col;

	public Space(Letter Letter, int Row, int Col) {
		this.letter = Letter;
		this.row = Row;
		this.col = Col;
	}

	public Space(BonusSquare bs, int Row, int Col) {
		super();
		this.bonusSquare = bs;
		this.row = Row;
		this.col = Col;
	}

	public Space() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getRow(), this.getCol());
	}

	@Override
	public boolean equals(Object obj) {
		Space s = (Space) obj;
		return (s.getCol() == this.getCol() && s.getRow() == this.getRow());
	}

	public BonusSquare getBonusSquare() {
		return bonusSquare;
	}

	public Letter getLetter() {
		return letter;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

}
