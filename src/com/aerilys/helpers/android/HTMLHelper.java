package com.aerilys.helpers.android;
public final class HTMLHelper
{
	/*
	* Allow to convert html text to plain text
	*/
	public static String convertFromHTML(String chaine)
	{
		return chaine.replace("&eacute;", "é").replace("&egrave;", "è").replace("&agrave;", "à")
				.replace("&ecirc;", "ê").replace("&ocirc;", "ô")
				.replace("&ucirc;", "û").replace("&ugrave;", "ù").replace("&ccedil;", "ç").replace("&acirc;", "â");
	}
}
