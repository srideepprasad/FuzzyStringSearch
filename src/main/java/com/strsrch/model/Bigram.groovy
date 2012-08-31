package com.strsrch.model

import java.util.Arrays;

class Bigram implements Comparable{
	private def byte[] data = null
	private def int value;
	
	Bigram(def byte[] data){
		this.data = data
		this.value = (data[1] << 8) | data[0]
	}

	def byte[] getData(){
		return this.data
	}
	
	def int getValue(){
		return this.value
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(data);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bigram other = (Bigram) obj;
		return obj.getValue() == this.value
	}

	public int compareTo(Object arg0) {
		if (!(obj instanceof Bigram)) throw new IllegalArgumentException("Illegal comparison")
		
		Bigram input = (Bigram) obj
		return input.getValue() - this.value
	}
	
	
}
