package com.strsrch.support
import java.util.Comparator

import org.apache.commons.lang3.StringUtils

import com.strsrch.model.SrchResult

class LevensteinComparator implements Comparator
{

	private String compareToStr;
	LevensteinComparator(String compareToStr){
		this.compareToStr = compareToStr
	}
	
	public int compare(Object obj1, Object obj2) {
		if (!(obj1 instanceof SrchResult) || !(obj2 instanceof SrchResult)) throw new IllegalArgumentException("Unsupported comparison")
		
		if (obj1.getPercentageMatch() != obj2.getPercentageMatch()) {
			return 0
		}else
		{
			return StringUtils.getLevenshteinDistance(obj1.getData(), compareToStr) - StringUtils.getLevenshteinDistance(obj2.getData(), compareToStr)
		} 
		
	}
	
}
