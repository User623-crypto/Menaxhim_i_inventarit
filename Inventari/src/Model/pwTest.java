package Model;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class pwTest {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		
		for(int i=0;i<10;i++)
		System.out.printf("Salt %d: %s%n",i,Arrays.toString(Password.getSalt()));
	}

}
