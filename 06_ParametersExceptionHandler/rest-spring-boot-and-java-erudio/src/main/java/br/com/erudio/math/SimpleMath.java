package br.com.erudio.math;


public class SimpleMath {
	public Double sum( Double numberOne, Double numberTwo) {
		
		return numberOne + numberTwo;
	}

	public Double subtract( Double numberOne, Double numberTwo) {
		
		return numberOne - numberTwo;
	}
	
	public Double multiplication( Double numberOne, Double numberTwo) {
	
		return numberOne * numberTwo;
	}
	
	public Double division( Double numberOne, Double numberTwo) {
	
		return numberOne / numberTwo;
	}
	
	public Double avg( Double numberOne, Double numberTwo) {
	
		return (numberOne + numberTwo) / 2;
	}
	
	public Double sqrRoot( Double number) {
	
		return Math.sqrt(number);
	}
}
