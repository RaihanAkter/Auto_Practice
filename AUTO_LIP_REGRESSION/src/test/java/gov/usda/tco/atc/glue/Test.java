package gov.usda.tco.atc.glue;

import gov.usda.atc.PiiSecurity;

public class Test {

	public static void main(String[] args) {
		
		System.out.println(PiiSecurity.getSecurity().encrypt("4WDCtcoID#$1"));

	}

}
