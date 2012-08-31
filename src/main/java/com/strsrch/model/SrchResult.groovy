package com.strsrch.model

import org.apache.commons.lang3.StringUtils

class SrchResult implements Comparable{
	def private String data
	def private double perMatch
	
	SrchResult(def String data, def double perMatch){
		this.data = data
		this.perMatch = perMatch
	}

	def String getData(){
		return this.data
	}
	
	def double getPercentageMatch(){
		return this.perMatch
	} 
	
	public int compareTo(Object o) {
		if (!(o instanceof SrchResult))throw new IllegalArgumentException("Invalid comparison")
		SrchResult input = (SrchResult) o
		return Math.round((input.perMatch - this.perMatch) * 100) //Just a scaling factor to try and factor decimal parts!

	}
}
