package week5;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EditDistanceTest {

	@Test
	void testEditDistance() {
		String s = "editing";
		String t = "distance";
		assertEquals(EditDistance.EditDistance(s, t), EDIST.editDistDP(s, t, s.length(), t.length()));
	}

	@Test
	void testEditDistance2() {
		String s = "12345";
		String t = "54321";
		assertEquals(EditDistance.EditDistance(s, t), EDIST.editDistDP(s, t, s.length(), t.length()));	}
	@Test
	void testEditDistance3() {
		String s = "ad";
		String t = "ad";
		assertEquals(EditDistance.EditDistance(s, t), EDIST.editDistDP(s, t, s.length(), t.length()));
		}

	@Test
	void testEditDistance5() {
		String s = "111";
		String t = "11111111";
		assertEquals(EditDistance.EditDistance(s, t), EDIST.editDistDP(s, t, s.length(), t.length()));
	}
	@Test
	void testEditDistance4() {
		String s = "ports";
		String t = "short";
		assertEquals(EditDistance.EditDistance(s, t), EDIST.editDistDP(s, t, s.length(), t.length()));
	}
	@Test
	void testEditDistance6() {
		String t = "aaajjj";
		String s = "jjjkkkk";
		assertEquals(EditDistance.EditDistance(s, t), EDIST.editDistDP(s, t, s.length(), t.length()));
		}
	
	@Test
	void testEditDistance7() {
		String s = "fine";
		String t = "afinea";
		assertEquals(EditDistance.EditDistance(s, t), EDIST.editDistDP(s, t, s.length(), t.length()));
	}
	
	@Test
	void testEditDistance8() {
		  String s = "00000100";
			String t = "10000000";
			assertEquals(EditDistance.EditDistance(s, t), EDIST.editDistDP(s, t, s.length(), t.length()));	}
	@Test
	void testEditDistance9() {
		  String s = "00000100";
			String t = "111111";
			assertEquals(EditDistance.EditDistance(s, t), EDIST.editDistDP(s, t, s.length(), t.length()));
	}
	@Test
	void testEditDistance10() {
		  String s = "abcdefg";
			String t = "aibicidieifigi";
			assertEquals(EditDistance.EditDistance(s, t), EDIST.editDistDP(s, t, s.length(), t.length()));
	}
	@Test
	void testEditDistance11() {
		  String s = "gataca";
			String t = "gttaccaca";
			assertEquals(EditDistance.EditDistance(s, t), EDIST.editDistDP(s, t, s.length(), t.length()));
			}
	
	@Test
	void testEditDistance12() {
		  String s = "saturday";
			String t = "sunday";
			assertEquals(EditDistance.EditDistance(s, t), EDIST.editDistDP(s, t, s.length(), t.length()));
	}
	
	@Test
	void testEditDistance13() {
		  String s = "fuckthisshit";
			String t = "fktjsjsit";
			assertEquals(EditDistance.EditDistance(s, t), EDIST.editDistDP(s, t, s.length(), t.length()));
	}
	
	@Test
	void testEditDistance14() {
		  String s = "jjjjjjjjjjkjjjjjjjjj";
			String t = "k";
			assertEquals(EditDistance.EditDistance(s, t), EDIST.editDistDP(s, t, s.length(), t.length()));
	}
	
	@Test
	void testEditDistance15() {
		  String s = "jjjjjjjjjjkkkkjjjjjj";
			String t = "k";
			assertEquals(EditDistance.EditDistance(s, t), EDIST.editDistDP(s, t, s.length(), t.length()));
	}
	


	
	@Test
	void testEditDistance17() {
		  String s = "jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj";
			String t = "k";
			assertEquals(EditDistance.EditDistance(s, t), EDIST.editDistDP(s, t, s.length(), t.length()));
	}
	
	
	@Test
	void testEditDistance18() {
	    String t = "intrinsic";
	    String s = "intrusive";
	    assertEquals(EditDistance.EditDistance(s, t), EDIST.editDistDP(s, t, s.length(), t.length()));
	}
	
	@Test
	void testEditDistance19() {
		  String s = "1n2b345678";
			String t = "nobigdeal";
			assertEquals(EditDistance.EditDistance(s, t), EDIST.editDistDP(s, t, s.length(), t.length()));
	}
	
	@Test
	void testEditDistance20() {
		  String s = "12345678";
			String t = "nnnnnnn8";
			assertEquals(EditDistance.EditDistance(s, t), EDIST.editDistDP(s, t, s.length(), t.length()));
	}
	@Test
	void testEditDistance21() {
		  String s = "12345678";
			String t = "nn8nnnnn8";
			assertEquals(EditDistance.EditDistance(s, t), EDIST.editDistDP(s, t, s.length(), t.length()));
	}
	
	@Test
	void testEditDistance22() {
		  String s = "abcdej";
			String t = "kabcdef";
			assertEquals(EditDistance.EditDistance(s, t), EDIST.editDistDP(s, t, s.length(), t.length()));
	}
}        
