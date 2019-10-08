package com.bjsxt.io.pattern;

public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Voice v =new Voice();
		v.say();
		Amplifier am =new Amplifier(v);
		am.say();
		
	}

}
