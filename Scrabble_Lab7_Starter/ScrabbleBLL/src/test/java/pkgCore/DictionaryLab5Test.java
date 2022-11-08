package pkgCore;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import pkgHelper.Util;

public class DictionaryLab5Test {

	@Test
	public void TestWordFilter1() {
		Util.PrintStart(new Throwable().getStackTrace()[0].getMethodName());
		Dictionary d = new Dictionary();
		String strLetters = "CLASS";

		ArrayList<Word> words = d.GenerateWords(strLetters);

		StringBuilder sb = new StringBuilder();
		String prefix = "";
		for (Word w: words) {
		  sb.append(prefix);
		  prefix = ", ";
		  sb.append(w.getWord());
		}
		assertEquals(15,words.size());
		System.out.println(sb.toString());
		Util.PrintEnd(new Throwable().getStackTrace()[0].getMethodName());
	}

	@Test
	public void TestWordFilter2() {
		Util.PrintStart(new Throwable().getStackTrace()[0].getMethodName());
		Dictionary d = new Dictionary();
		String strLetters = "CLASS";
		WordFilter WF = new WordFilter();
		WF.setStrStartWith("L");
		WF.setiLength(3);

		ArrayList<Word> words = d.GenerateWords(strLetters, WF);

		StringBuilder sb = new StringBuilder();
		String prefix = "";
		for (Word w: words) {
		  sb.append(prefix);
		  prefix = ", ";
		  sb.append(w.getWord());
		}
		assertEquals(2,words.size());
		System.out.println(sb.toString());
		Util.PrintEnd(new Throwable().getStackTrace()[0].getMethodName());
	}
	
	
	
	
	
	
	@Test
	public void TestWordFilterFrancis() {
		Util.PrintStart(new Throwable().getStackTrace()[0].getMethodName());
		Dictionary d = new Dictionary();
		String strLetters = "ABDF?ES";
		WordFilter wf = new WordFilter();
		wf.setiLength(5);

		ArrayList<Word> words = d.GenerateWords(strLetters, wf);

		StringBuilder sb = new StringBuilder();
		String prefix = "";
		for (Word w: words) {
		  sb.append(prefix);
		  prefix = ", ";
		  sb.append(w.getWord());
		}
		System.out.println(sb.toString());
		Util.PrintEnd(new Throwable().getStackTrace()[0].getMethodName());
	}
}
