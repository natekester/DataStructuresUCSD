package week3;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import week3.OriginalPhoneBook.Query;
import week3.PhoneBook.ChainLink;
import week3.PhoneBook.Contact;

class PhoneBookTest {

	@Test
	void test() {

		
		PhoneBook p = new PhoneBook();
		PhoneBook check = new PhoneBook();
		check.processQueriesCheck();
		
		System.out.println("next: ");
		p.processQueries();
		for( int i = 0; i< p.outPut.size(); i++)
		{
		assertEquals(p.outPut.get(i), check.outPut.get(i));
		}
		
	}

	void test1() {


		
	}

}
