package pkgGame;

public class Letter {

	private Character chLetter;
	private int iScore;
	
	public Letter(Character chLetter) {
		super();
		this.chLetter = chLetter;
		SetDefaultScore(chLetter);
	}
	
	public Character getChLetter() {
		return chLetter;
	}

	public int getiScore() {
		return iScore;
	}

	private void SetDefaultScore(Character letter)
	{
//		0 Points - Blank tile.
//		1 Point - A, E, I, L, N, O, R, S, T and U.
//		2 Points - D and G.
//		3 Points - B, C, M and P.
//		4 Points - F, H, V, W and Y.
//		5 Points - K.
//		8 Points - J and X.
//		10 Points - Q and Z.
		
		iScore = switch (letter) {

	    case 'A':
	    case 'E':
	    case 'I':
	    case 'L':
	    case 'N':
	    case 'O':
	    case 'R':
	    case 'S':
	    case 'T':
	    case 'U':
	        yield 1;
	    case 'D':
	    case 'G':
	        yield 2;
		case 'B':
		case 'C':
		case 'M':
		case 'P':
			yield 3;	
		case 'F':
		case 'H':
		case 'V':
		case 'W':
		case 'Y':
			yield 4;	
		case 'K':
			yield 5;
		case 'J':
		case 'X':
			yield 8;
		case 'Q':
		case 'Z':
			yield 10;			
	    // No default
		default:
			throw new IllegalArgumentException("Unexpected value: " + letter);
			};	
	}
	
	
	
}
