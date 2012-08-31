package com.strsrch.main

import com.strsrch.model.Bigram
import com.strsrch.model.SrchResult
import com.strsrch.support.LevensteinComparator

class FuzzyStringComparator {
	
	def List<SrchResult> fuzzyCompareAndRank(List<String> candidates, String input){
		List<SrchResult> returnList = []
		candidates.each { candidate ->
			returnList.add(new SrchResult(candidate, fuzzyCompare(candidate, input)))
		}
		
		Collections.sort(returnList)
		Collections.sort(returnList, new LevensteinComparator(input))
		return returnList
	}
	
	def double fuzzyCompare(String candidate,String compareTo ){
		candidate = candidate.toLowerCase().replaceAll("\\s+", " ").trim()
		compareTo = compareTo.toLowerCase().replaceAll("\\s+", " ").trim()

		List<String> candidateTokens = candidate.tokenize()
		List<String> compareToTokens = compareTo.tokenize()
		double totalScore = 0
	
		int compareToTokenSize = compareToTokens.size()
		int candidateTokenSize = candidateTokens.size()
		int bestPossibleScore = (compareToTokenSize > candidateTokenSize) ? compareToTokenSize : candidateTokenSize	
		
		candidateTokens.each { candidateToken ->
			double bestScore = 0
			String bestCandidate = null
			compareToTokens.each { compareToToken ->
				double score = compareInternal(candidateToken, compareToToken)
				
				if (score > bestScore){
					bestScore = score
					bestCandidate = compareToToken
				}
			}
			totalScore = totalScore + bestScore
			compareToTokens.remove(bestCandidate)
		}
	
		
		return (totalScore / bestPossibleScore) * 100		
	}

	
	private double compareInternal(String candidate,String compareTo ){
		int matches = 0
		List<Bigram> candidateBigrams = tokenize(candidate)
		List<Bigram> inputBigrams = tokenize(compareTo.toLowerCase().replaceAll("\\s+", " ").trim())
		inputBigrams.each { element ->
			matches = matches + candidateBigrams.findAll {
				it.getValue() == element.getValue()
			}.size()
		}
		return ((2 * (matches)) / (inputBigrams.size() + candidateBigrams.size()))
	}

	private def List<Bigram> tokenize(String str){
		List<Bigram> retList = []
		byte[] inputData = str.getBytes()
		int i=0
		for (i=0;i<inputData.length ;i=i+2){
			byte[] data = new byte[2];
			data[0] = inputData[i]
			data[1] = (i+1) < inputData.size() ? inputData[i + 1] : 0
			retList.add(new Bigram(data))
		}
		return retList
	}

}
