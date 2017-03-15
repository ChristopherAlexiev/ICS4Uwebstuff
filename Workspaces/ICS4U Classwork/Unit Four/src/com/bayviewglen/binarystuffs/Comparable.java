package com.bayviewglen.binarystuffs;

public interface Comparable {
	int compareTo(Comparable comparable);
	String toStringForSave();
	boolean contactContainsInfo(Comparable comparable);
}
